package com.aihs.coupon.template.impl;

import com.aihs.coupon.template.AbstractRuleTemplate;
import com.aihs.coupon.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class LonelyNightTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        if(hourOfDay >= 23 && hourOfDay <2 ){
            quota *= 2;
        }
        Long benefitAmount = shopTotalAmount < quota ? shopTotalAmount:quota;
        return orderTotalAmount-benefitAmount;
    }
}
