package com.murad.operationsservice.configuration;

import com.google.gson.Gson;
import com.murad.operationsservice.dto.PaymentRequest;
import com.murad.operationsservice.dto.SendingPaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@Slf4j
public class RequestHelper {
    private final Gson gson;

    public <T> ResponseEntity<String> sendPost(String url, T dto) throws Exception {
        // Создание доверительного менеджера, который принимает все сертификаты
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };



        // Настройка SSL контекста
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());

        // Создание фабрики SSL сокетов и отключение проверки хоста
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        // Создание RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Установка заголовков
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Выполнение POST-запроса
        return restTemplate.postForEntity(url, dto, String.class);
    }
    public PaymentRequest getPaymentUrl(String url,SendingPaymentDto dto){
        // Создание объекта для отправки запросов
        RestTemplate restTemplate = new RestTemplate();


        // Преобразование объекта в JSON-строку
        String jsonRequest = gson.toJson(dto);

        // Создание заголовков запроса
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Создание объекта HttpEntity с JSON-строкой и заголовками
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest, headers);

        // Выполнение POST-запроса
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Обработка ответа
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();

            // Преобразование JSON-ответа в объект
            PaymentRequest responseData = gson.fromJson(jsonResponse, PaymentRequest.class);
            return responseData;
        } else {
            log.error("Request failed with status code: " + responseEntity.getStatusCode());
            return PaymentRequest.builder().error(true).message("balance server don't answer. Error code"+responseEntity.getStatusCode())
                    .build();
        }
    }
}
