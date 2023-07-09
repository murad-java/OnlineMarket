package com.murad.operationsservice.repository;

import com.murad.operationsservice.entity.BuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyRepository extends JpaRepository<BuyEntity,Long> {
    List<BuyEntity> findByUserIdAndAndPay(long userid,boolean isPay);
    List<BuyEntity> findByUserIdAndAndError(long userid,boolean isError);
    List<BuyEntity> findByUuid(String uuid);
}
