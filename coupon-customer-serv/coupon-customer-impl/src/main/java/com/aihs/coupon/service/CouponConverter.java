package com.aihs.coupon.service;

import com.aihs.coupon.beans.CouponInfo;
import com.aihs.coupon.entity.Coupon;

public class CouponConverter {
    public static CouponInfo convert(Coupon coupon){
        return  CouponInfo.builder()
                .id(coupon.getId())
                .status(coupon.getStatus().getCode())
                .templateId(coupon.getTemplateId())
                .shopId(coupon.getShopId())
                .userId(coupon.getUserId())
                .template(coupon.getTemplateInfo())
                .build();
    }
}
