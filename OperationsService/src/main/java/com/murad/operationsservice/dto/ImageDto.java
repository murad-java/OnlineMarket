package com.murad.operationsservice.dto;

import lombok.Data;

@Data
public class ImageDto {
    long id;
    String url;
    byte[] img;
}
