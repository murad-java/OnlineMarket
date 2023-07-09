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
public class PaymentReturnDto {
    private long userId;
    private BigDecimal money;
    private boolean error;
    private String message;
    private int hash;

    @Override
    public String toString() {
        return "PaymentReturnDto{" +
                "userId=" + userId +
                ", money=" + money +
                ", error=" + error +
                ", message='" + message + '\'' +
                ", hash=" + hash +
                '}';
    }
}
