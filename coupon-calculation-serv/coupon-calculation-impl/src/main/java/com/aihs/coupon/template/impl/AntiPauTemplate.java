package com.aihs.coupon.template.impl;

import com.aihs.coupon.template.AbstractRuleTemplate;
import com.aihs.coupon.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AntiPauTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        return orderTotalAmount * 996;
    }

    @Override
    protected Long minCost() {
        return 996L;
    }
}
