package com.aihs.coupon.template;

import com.aihs.coupon.beans.CouponTemplateInfo;
import com.aihs.coupon.beans.ShoppingCart;
import com.aihs.coupon.enums.CouponType;
import com.aihs.coupon.template.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Slf4j
@Component
public class CouponTemplateFactory {
    @Autowired
    private MoneyOffTemplate moneyOffTemplate;
    @Autowired
    private DiscountTemplate discountTemplate;
    @Autowired
    private RandomReductionTemplate randomReductionTemplate;
    @Autowired
    private LonelyNightTemplate lonelyNightTemplate;
    @Autowired
    private DummyTemplate dummyTemplate;
    @Autowired
    private AntiPauTemplate antiPauTemplate;

    public RuleTemplate getTemplate(ShoppingCart order){
        if(CollectionUtils.isEmpty(order.getProducts())){
            return dummyTemplate;
        }
        CouponTemplateInfo templateInfo = order.getCouponInfos().get(0).getTemplate();
        CouponType category = CouponType.convert(templateInfo.getType());

        switch (category){
            case MONEY_OFF:
                return moneyOffTemplate;
            case DISCOUNT:
                return discountTemplate;
            case RANDOM_DISCOUNT:
                return randomReductionTemplate;
            case LONELY_NIGHT_MONEY_OFF:
                return lonelyNightTemplate;
            case ANTI_PUA:
                return antiPauTemplate;
            default:
                return dummyTemplate;
        }
    }
}
