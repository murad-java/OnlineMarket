package com.murad.userservice.entity;

import com.murad.userservice.model.Authority;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users", indexes = {
        @Index(name = "idx_userentity_username", columnList = "username")
})
public class UserEntity {
    @Id
    @GeneratedValue
    private Long Id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Boolean active=true;
    @Column(nullable = false)
    private LocalDateTime localDateTime=LocalDateTime.now();
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String photoUrl;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority role;
}
