package com.aihs.coupon.service.intf;

import com.aihs.coupon.beans.*;
import com.aihs.coupon.entity.Coupon;

import java.util.List;

public interface CouponCustomerService {
    Coupon requestCoupon(RequestCoupon requestCoupon);

    ShoppingCart placeOrder(ShoppingCart shoppingCart);

    SimulationResponse simulateOrderPrice(SimulationOrder order);

    void deleteCoupon(Long userId,Long couponId);

    List<CouponInfo> findCoupon(SearchCoupon coupon);
}
