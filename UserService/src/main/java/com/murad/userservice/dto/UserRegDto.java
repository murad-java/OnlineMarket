package com.murad.userservice.dto;

import com.murad.userservice.model.Authority;
import com.murad.userservice.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    private String photoUrl;

    public static UserRegDto fromUserEntity(UserEntity userEntity) {
        UserRegDto userRegDto = new UserRegDto();
        userRegDto.setUsername(userEntity.getUsername());
        userRegDto.setPassword(userEntity.getPassword());
        userRegDto.setEmail(userEntity.getEmail());
        userRegDto.setAddress(userEntity.getAddress());
        userRegDto.setPhone(userEntity.getPhone());
        userRegDto.setPhotoUrl(userEntity.getPhotoUrl());
        return userRegDto;
    }


    public UserEntity toUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(this.getUsername());
        userEntity.setPassword(this.getPassword());
        userEntity.setEmail(this.getEmail());
        userEntity.setAddress(this.getAddress());
        userEntity.setActive(true);
        userEntity.setLocalDateTime(LocalDateTime.now());
        userEntity.setPhone(this.getPhone());
        userEntity.setPhotoUrl(this.getPhotoUrl());
        userEntity.setRole(Authority.USER);
        return userEntity;
    }
}
