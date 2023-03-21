package com.aihs.coupon;

import com.aihs.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponDao extends JpaRepository<Coupon,Long> {
    long countByUserIdAndTemplateId(Long userId,Long templateId);
}
