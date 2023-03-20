package com.aihs.coupon.template;

import com.aihs.coupon.beans.CouponTemplateInfo;
import com.aihs.coupon.beans.Product;
import com.aihs.coupon.beans.ShoppingCart;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractRuleTemplate implements RuleTemplate{
    @Override
    public ShoppingCart calculate(ShoppingCart settlement) {
        //获取订单总价
        Long orderTotalAmount = getTotalPrice(settlement.getProducts());
        //获取以shopId为维度的价格统计
        Map<Long,Long> sumAmount = getTotalPriceGroupByShop(settlement.getProducts());
        //一下规则只做单个优惠券的计算
        CouponTemplateInfo template = settlement.getCouponInfos().get(0).getTemplate();
        //最低消费限制
        Long threshold = template.getRule().getDiscount().getThreshold();
        //优惠金额或者打折比例
        Long quota = template.getRule().getDiscount().getQuota();
        //当前优惠券适用的门店ID
        Long shopId = template.getShopId();
        Long shopTotalAmount = (shopId == null)?orderTotalAmount:sumAmount.get(shopId);
        if(shopTotalAmount == null||shopTotalAmount<threshold){
            log.warn("Totals of amount not meet,or coupons are not applicable to this order");
            settlement.setCost(orderTotalAmount);
            settlement.setCouponInfos(Collections.emptyList());
            return settlement;
        }
        //子类中计算新的价格
        Long newCost = calculateNewPrice(orderTotalAmount,shopTotalAmount,quota);
        if(newCost < minCost()){
            newCost = minCost();
        }
        settlement.setCost(newCost);
        log.debug("origin price={},new price={}",orderTotalAmount,newCost);
        return settlement;
    }

    protected Long getTotalPrice(List<Product> products){
        return products.stream().mapToLong(product -> product.getPrice()*product.getCount())
                .sum();
    }

    protected Map<Long,Long> getTotalPriceGroupByShop(List<Product> products){
        Map<Long,Long> groups = products.stream()
                .collect(Collectors.groupingBy(m->m.getShopId(),Collectors.summingLong(p->p.getPrice()*p.getCount())));
        return groups;
    }

    //每个订单至少支付1分钱
    protected Long minCost(){
        return 1L;
    }

    protected Long convertToDecimal(Double value){
        return new BigDecimal(value).setScale(0, RoundingMode.HALF_UP).longValue();
    }

    abstract protected Long calculateNewPrice(Long orderTotalAmount,Long shopTotalAmount,Long quota);
}
