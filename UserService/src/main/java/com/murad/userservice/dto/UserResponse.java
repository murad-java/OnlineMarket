package com.murad.userservice.dto;


import com.murad.userservice.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String photoUrl;

    public static UserResponse fromUserEntity(UserEntity user) {
        return new UserResponse(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getAddress(),
                user.getPhone(),
                user.getPhotoUrl());
    }
}

