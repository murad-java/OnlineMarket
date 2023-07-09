package com.murad.operationsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendingPaymentDto {

    long tranId;
    long uid;
    BigDecimal amount;
    String redirectServiceURL;
    String language; //az,ru,en

    String fullName;
    String cardNumber;
    String cardMonth;
    String cardYear;
    String cardCVC;


}
