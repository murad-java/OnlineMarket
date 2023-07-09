package com.murad.operationsservice.model;

import com.murad.operationsservice.dto.PaymentReturnDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HashIdGeneration {

    public long getHash( Object ... values){
        return Objects.hash(values);
    }

    public static int Hash(PaymentReturnDto dto){
        return Objects.hash(dto.getUserId(),dto.getUserId()+""+dto.getMoney(),dto.getMoney(),1988);
    }
    public static boolean checkHash(PaymentReturnDto dto){
        return dto.getHash() == Hash(dto);
    }
}
