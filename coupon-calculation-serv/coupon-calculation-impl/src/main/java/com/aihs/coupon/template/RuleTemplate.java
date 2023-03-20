package com.aihs.coupon.template;

import com.aihs.coupon.beans.ShoppingCart;

public interface RuleTemplate {
    ShoppingCart calculate(ShoppingCart settlement);
}
