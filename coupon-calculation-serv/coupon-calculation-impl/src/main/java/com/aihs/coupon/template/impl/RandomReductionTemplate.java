package com.aihs.coupon.template.impl;

import com.aihs.coupon.template.AbstractRuleTemplate;
import com.aihs.coupon.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class RandomReductionTemplate extends AbstractRuleTemplate implements RuleTemplate {

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        Long maxBenefit = Math.min(shopTotalAmount,quota);
        int reductionAmount = new Random().nextInt(maxBenefit.intValue());
        Long newCost = orderTotalAmount -reductionAmount;
        log.debug("origin price={},new price={}",orderTotalAmount,newCost);
        return newCost;
    }
}
