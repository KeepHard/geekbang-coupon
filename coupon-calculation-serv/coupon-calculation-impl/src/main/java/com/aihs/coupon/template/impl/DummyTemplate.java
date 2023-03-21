package com.aihs.coupon.template.impl;

import com.aihs.coupon.beans.ShoppingCart;
import com.aihs.coupon.template.AbstractRuleTemplate;
import com.aihs.coupon.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DummyTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    public ShoppingCart calculate(ShoppingCart settlement) {
        Long orderTotalAmount = getTotalPrice(settlement.getProducts());
        settlement.setCost(orderTotalAmount);
        return settlement;
    }

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        return orderTotalAmount;
    }
}
