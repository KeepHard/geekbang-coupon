package com.aihs.coupon.service.intf;

import com.aihs.coupon.beans.ShoppingCart;
import com.aihs.coupon.beans.SimulationOrder;
import com.aihs.coupon.beans.SimulationResponse;

public interface CouponCalculationService {
    ShoppingCart calculateOrderPrice(ShoppingCart cart);
    SimulationResponse simulateOrder(SimulationOrder cart);
}
