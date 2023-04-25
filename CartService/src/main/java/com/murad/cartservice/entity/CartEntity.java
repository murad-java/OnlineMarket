package com.murad.cartservice.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart",indexes = {
        @Index(name = "idx_cart_user_id", columnList = "user_id")
})
@Data
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_id",nullable = false)
    Long userId;
    @Column(name = "product_id",nullable = false)
    Long productId;
    @Column(name = "count",nullable = false)
    int count =1;
    @Column(name = "add_date_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime addDateTime;


}
