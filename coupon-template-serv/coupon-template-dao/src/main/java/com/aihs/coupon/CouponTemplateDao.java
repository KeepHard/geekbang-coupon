package com.aihs.coupon;

import com.aihs.coupon.entity.CouponTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponTemplateDao extends JpaRepository<CouponTemplate,Long> {
    List<CouponTemplate> findAllByShopId(Long shopId);

    Page<CouponTemplate> findAllByIdIn(List<Long> Id, Pageable page);

    Integer countByShopIdAndAvailable(Long shopId, Boolean available);

    @Modifying
    @Query("update CouponTemplate c set c.available = 0 where c.id = :id")
    int makeCouponUnavailable(Long id);
}
