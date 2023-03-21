package com.aihs.coupon.service;

import com.aihs.coupon.beans.CouponInfo;
import com.aihs.coupon.beans.ShoppingCart;
import com.aihs.coupon.beans.SimulationOrder;
import com.aihs.coupon.beans.SimulationResponse;
import com.aihs.coupon.service.intf.CouponCalculationService;
import com.aihs.coupon.template.CouponTemplateFactory;
import com.aihs.coupon.template.RuleTemplate;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CouponCalculationServiceImpl implements CouponCalculationService {

    @Autowired
    private CouponTemplateFactory couponTemplateFactory;

    @Override
    public ShoppingCart calculateOrderPrice(ShoppingCart cart) {
        log.info("calculate order price:{}", JSON.toJSONString(cart));
        RuleTemplate ruleTemplate = couponTemplateFactory.getTemplate(cart);
        return ruleTemplate.calculate(cart);
    }

    @Override
    public SimulationResponse simulateOrder(SimulationOrder order) {
        SimulationResponse response = new SimulationResponse();
        Long minOrderPrice = Long.MAX_VALUE;

        for(CouponInfo couponInfo:order.getCouponInfos()){
            ShoppingCart cart = new ShoppingCart();
            cart.setProducts(order.getProducts());
            cart.setCouponInfos(Lists.newArrayList(couponInfo));
            cart = couponTemplateFactory.getTemplate(cart).calculate(cart);
            Long couponId = couponInfo.getId();
            Long orderPrice = cart.getCost();
            response.getCouponToOrderPrice().put(couponId,orderPrice);
            if(minOrderPrice>orderPrice){
                response.setBestCouponId(couponId);
                minOrderPrice = orderPrice;
            }
        }
        return response;
    }
}
