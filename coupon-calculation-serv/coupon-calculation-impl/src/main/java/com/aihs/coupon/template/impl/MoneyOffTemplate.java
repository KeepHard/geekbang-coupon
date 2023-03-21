package com.aihs.coupon.template.impl;

import com.aihs.coupon.template.AbstractRuleTemplate;
import com.aihs.coupon.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MoneyOffTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        Long benefitAmount = shopTotalAmount < quota?shopTotalAmount:quota;
        return orderTotalAmount - benefitAmount;
    }
}
