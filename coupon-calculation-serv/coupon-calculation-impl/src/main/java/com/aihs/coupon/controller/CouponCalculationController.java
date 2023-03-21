package com.aihs.coupon.controller;

import com.aihs.coupon.beans.ShoppingCart;
import com.aihs.coupon.beans.SimulationOrder;
import com.aihs.coupon.beans.SimulationResponse;
import com.aihs.coupon.service.intf.CouponCalculationService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/calculator")
public class CouponCalculationController {

    @Autowired
    private CouponCalculationService couponCalculationService;

    @PostMapping("/checkout")
    @ResponseBody
    public ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart settlement){
        log.info("do calculation:{}", JSON.toJSONString(settlement));
        return couponCalculationService.calculateOrderPrice(settlement);
    }

    @PostMapping("/simulate")
    @ResponseBody
    public SimulationResponse simulate(@RequestBody SimulationOrder simulator){
        log.info("do simulation:{}",JSON.toJSONString(simulator));
        return couponCalculationService.simulateOrder(simulator);
    }
}
