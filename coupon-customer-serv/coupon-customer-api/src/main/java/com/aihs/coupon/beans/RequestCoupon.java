package com.aihs.coupon.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCoupon {
    @NotNull
    private Long userId;

    @NotNull
    private Long couponTemplateId;
}
