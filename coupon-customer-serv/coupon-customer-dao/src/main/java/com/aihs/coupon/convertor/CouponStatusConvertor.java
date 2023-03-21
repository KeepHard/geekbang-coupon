package com.aihs.coupon.convertor;

import com.aihs.coupon.enums.CouponStatus;

import javax.persistence.AttributeConverter;

public class CouponStatusConvertor implements AttributeConverter<CouponStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(CouponStatus couponStatus) {
        return couponStatus.getCode();
    }

    @Override
    public CouponStatus convertToEntityAttribute(Integer code) {
        return CouponStatus.convert(code);
    }
}
