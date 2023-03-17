package com.aihs.coupon.beans;

import com.aihs.coupon.beans.rules.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateInfo {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    //优惠券描述
    private String desc;

    @NotNull
    //优惠券类型
    private String type;

    //适用门店-若无则为通用券
    private Long shopId;

    @NotNull
    //优惠券规则
    private TemplateRule rule;

    private Boolean available;
}
