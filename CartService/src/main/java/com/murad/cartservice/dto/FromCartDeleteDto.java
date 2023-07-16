package com.murad.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FromCartDeleteDto {
    private long       userId;
    private List<Long> productIds;

    @Override
    public String toString() {
        return "FromCartDeleteDto{" +
                "userId=" + userId +
                ", productIds=" + productIds.toString() +
                '}';
    }
}
