package com.aihs.coupon.service;

import com.aihs.coupon.CouponDao;
import com.aihs.coupon.beans.*;
import com.aihs.coupon.entity.Coupon;
import com.aihs.coupon.enums.CouponStatus;
import com.aihs.coupon.service.intf.CouponCalculationService;
import com.aihs.coupon.service.intf.CouponCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.aihs.coupon.service.intf.CouponTemplateService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class CouponCustomerServiceImpl implements CouponCustomerService {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private CouponTemplateService templateService;

    @Autowired
    private CouponCalculationService calculationService;

    @Override
    public Coupon requestCoupon(RequestCoupon requestCoupon) {
        CouponTemplateInfo templateInfo = templateService.loadTemplateInfo(requestCoupon.getCouponTemplateId());
        if(templateInfo == null){
            log.error("invalid template id:{}",requestCoupon.getCouponTemplateId());
            throw new IllegalArgumentException("template is unavailable");
        }
        Long count = couponDao.countByUserIdAndTemplateId(requestCoupon.getUserId(), requestCoupon.getCouponTemplateId());
        if(count >= templateInfo.getRule().getLimitation()){
            log.error("exceeds maximum number");
            throw new IllegalArgumentException("exceeds maximum number");
        }
        Coupon coupon = Coupon.builder()
                .templateId(requestCoupon.getCouponTemplateId())
                .userId(requestCoupon.getUserId())
                .shopId(templateInfo.getShopId())
                .status(CouponStatus.AVAILABLE)
                .build();
        couponDao.save(coupon);
        return coupon;
    }

    @Override
    @Transactional
    public ShoppingCart placeOrder(ShoppingCart shoppingCart) {
        if(CollectionUtils.isEmpty(shoppingCart.getProducts())){
            log.error("invalid check out request,order={}",shoppingCart);
            throw new IllegalArgumentException("cart is empty");
        }
        Coupon coupon = null;
        if(shoppingCart.getCouponId() != null){
            Coupon example = Coupon.builder()
                    .userId(shoppingCart.getUserId())
                    .id(shoppingCart.getCouponId())
                    .status(CouponStatus.AVAILABLE)
                    .build();
            coupon = couponDao.findAll(Example.of(example))
                    .stream()
                    .findFirst()
                    .orElseThrow(()->new RuntimeException("Coupon not found"));
        }
        CouponInfo couponInfo = CouponConverter.convert(coupon);
        couponInfo.setTemplate(templateService.loadTemplateInfo(coupon.getTemplateId()));
        return null;
    }

    @Override
    public SimulationResponse simulateOrderPrice(SimulationOrder order) {
        return null;
    }

    @Override
    public void deleteCoupon(Long userId, Long couponId) {

    }

    @Override
    public List<CouponInfo> findCoupon(SearchCoupon coupon) {
        return null;
    }
}
