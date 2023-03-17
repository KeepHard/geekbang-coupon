package com.aihs.coupon.converter;

import com.aihs.coupon.enums.CouponType;

import javax.persistence.AttributeConverter;

public class CouponTypeConverter implements AttributeConverter<CouponType,String> {
    @Override
    public String convertToDatabaseColumn(CouponType couponType) {
        return couponType.getCode();
    }

    @Override
    public CouponType convertToEntityAttribute(String code) {
        return CouponType.convert(code);
    }
}
