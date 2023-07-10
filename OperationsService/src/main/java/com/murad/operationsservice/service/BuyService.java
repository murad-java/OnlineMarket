package com.murad.operationsservice.service;

import com.murad.operationsservice.configuration.CartServiceClient;
import com.murad.operationsservice.configuration.RequestHelper;
import com.murad.operationsservice.configuration.UserService;
import com.murad.operationsservice.dto.*;
import com.murad.operationsservice.entity.BuyEntity;
import com.murad.operationsservice.entity.ProductEntity;
import com.murad.operationsservice.exception.ResourceNotFoundException;
import com.murad.operationsservice.model.HashIdGeneration;
import com.murad.operationsservice.repository.BuyRepository;
import com.murad.operationsservice.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyService {
    private final BuyRepository buyRepository;
    private final UserService userService;
    private final ProductService productService;
    private final HashIdGeneration hashIdGeneration;
    private final RequestHelper requestHelper;
    private final CartServiceClient cartServiceClient;
    @Value("${server.my.url}")
    String returnUrl;
    @Value("${balance.server.url}")
    String URL;

    public PaymentRequest buyFromCart() {
        log.info("Получил команду на операцию оплаты");
        var user = getUser();
        log.info("USER ID {}", user.getId());
        var productBasket = cartServiceClient.getCart(user);
        PaymentRequest paymentRequest = null;
        log.info("Количество продукта в корзине {}, ID user {}", productBasket.getProducts().size(), user.getId());
        if (productBasket == null) return null;
        var uuid = hashIdGeneration.getHash(productBasket.getTotalPrice().toString(), UUID.randomUUID().toString());
        for (var cart : productBasket.getProducts()) {
            BuyEntity buyEntity = BuyEntity.builder()
                    .uuid(String.valueOf(uuid))
                    .price(cart.getPrice())
                    .productId(cart.getProductId())
                    .userId(user.getId())
                    .build();
            buyRepository.save(buyEntity);
        }

        var paymentDto = SendingPaymentDto.builder()
                .amount(productBasket.getTotalPrice())
                .language("ru")
                .uid(user.getId())
                .tranId(uuid)
                .redirectServiceURL(returnUrl).build();
        try {
            paymentRequest = requestHelper.getPaymentUrl(URL, paymentDto);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return paymentRequest;
    }

    private UserResponse getUser() {
        var userName = SecurityUtils.getCurrentUsername().orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found name in JWT!");
        });
        return userService.getUser(userName);
    }

    public void paymentReturn(PaymentReturnDto dto) {
        if (dto.isError()) {
            log.error(dto.getMessage());
        }
        List<BuyEntity> buyEntities = buyRepository.findByUuid(String.valueOf(dto.getUserId()));
        for (var buyEntity : buyEntities) {

            if (dto.isError()) {
                buyEntity.setError(true);
            } else {
                buyEntity.setPay(true);
            }

            buyEntity.setReturnCode(dto.getMessage());
            buyEntity.setReturnMessage(dto.getMessage());
            buyEntity.setDateTime(LocalDateTime.now());

            buyRepository.save(buyEntity);
        }
        ///////////////
        if (!dto.isError()) {
            if (buyEntities != null && buyEntities.size() > 0) {
                long userId = buyEntities.get(0).getUserId();
                List<Long> productIds = buyEntities.stream().map(buyEntity -> buyEntity.getProductId()).toList();
                FromCartDeleteDto fromCartDeleteDto = FromCartDeleteDto.builder()
                        .userId(userId)
                        .productIds(productIds).build();
                cartServiceClient.deleteAllFromCart(fromCartDeleteDto);
            }
        }
    }

    public PaymentRequest buyProduct(BuyProductDto buyProductDto) {
        var product = productService.getProductById(buyProductDto.getProductId());
        var uuid = hashIdGeneration.getHash(product.getPrice().toString(), UUID.randomUUID().toString());
        var user = getUser();
        PaymentRequest paymentRequest = null;
        BuyEntity buyEntity = BuyEntity.builder()
                .uuid(String.valueOf(uuid))
                .price(product.getPrice())
                .productId(product.getId())
                .userId(user.getId())
                .build();
        buyRepository.save(buyEntity);
        var paymentDto = SendingPaymentDto.builder()
                .amount(product.getPrice())
                .language("ru")
                .uid(user.getId())
                .tranId(uuid)
                .redirectServiceURL(returnUrl).build();
        try {
            paymentRequest = requestHelper.getPaymentUrl(URL, paymentDto);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return paymentRequest;
    }

    public List<BuyProductRequest> getBuyProducts() {
        var user= getUser();
        List<BuyProductRequest> buyProductRequests=new ArrayList<>();
        List<BuyEntity> buyEntities = buyRepository.findByUserIdAndPay(user.getId(),true);
        for(var buyEntity: buyEntities){
            ProductEntity productEntity = productService.getProductById(buyEntity.getProductId());
            byte[] image = productService.getFirstPhotoByProduct(productEntity.getId());
            BuyProductRequest productRequest = BuyProductRequest.builder()
                    .name(productEntity.getName())
                    .image(image)
                    .price(buyEntity.getPrice())
                    .buyDateTime(formatDateTime(buyEntity.getDateTime()))
                    .productFileEntity(ProductFileRequest.converData(productEntity.getFile()))
                    .build();
            buyProductRequests.add(productRequest);
        }
        return buyProductRequests;
    }

    private String formatDateTime(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }
}
