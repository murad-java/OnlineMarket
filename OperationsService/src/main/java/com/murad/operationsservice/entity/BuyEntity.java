package com.murad.operationsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Buy")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String uuid;
        Long userId;
        Long productId;
        LocalDateTime dateTime =LocalDateTime.now();
        BigDecimal price;
        boolean error=false;
        String returnCode;
        String returnMessage;
        boolean pay=false;

}
