package com.aihs.coupon.service.intf;

import com.aihs.coupon.beans.CouponTemplateInfo;
import com.aihs.coupon.beans.PagedCouponTemplateInfo;
import com.aihs.coupon.beans.TemplateSearchParams;

import java.util.Collection;
import java.util.Map;

public interface CouponTemplateService {
    CouponTemplateInfo createTemplate(CouponTemplateInfo request);

    CouponTemplateInfo cloneTemplate(Long templateId);

    PagedCouponTemplateInfo search(TemplateSearchParams request);

    CouponTemplateInfo loadTemplateInfo(Long templateId);

    void deleteTemplate(Long id);

    Map<Long,CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids);
}
