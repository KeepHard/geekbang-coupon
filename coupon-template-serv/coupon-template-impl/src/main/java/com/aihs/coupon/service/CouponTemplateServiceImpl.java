package com.aihs.coupon.service;

import com.aihs.coupon.CouponTemplateDao;
import com.aihs.coupon.beans.CouponTemplateInfo;
import com.aihs.coupon.beans.PagedCouponTemplateInfo;
import com.aihs.coupon.beans.TemplateSearchParams;
import com.aihs.coupon.converter.CouponTemplateConverter;
import com.aihs.coupon.entity.CouponTemplate;
import com.aihs.coupon.enums.CouponType;
import com.aihs.coupon.service.intf.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CouponTemplateServiceImpl implements CouponTemplateService{

    @Autowired
    private CouponTemplateDao templateDao;

    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        if(request.getShopId() != null){
            Integer count = templateDao.countByShopIdAndAvailable(request.getShopId(),true);
            if(count > 100){
                log.error("the totals of coupon template exceeds maximum number ");
                throw new UnsupportedOperationException("exceeds the maximum of coupon templates that you can create ");
            }
        }
        CouponTemplate template = CouponTemplate.builder()
                .name(request.getName())
                .description(request.getDesc())
                .category(CouponType.convert(request.getType()))
                .available(true)
                .shopId(request.getShopId())
                .rule(request.getRule())
                .build();
        template = templateDao.save(template);
        return CouponTemplateConverter.convertToTemplateInfo(template);
    }

    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        log.info("cloning template id: {}",templateId);
        CouponTemplate source = templateDao.findById(templateId).orElseThrow(() -> new IllegalArgumentException("invalid template ID"));
        CouponTemplate target = new CouponTemplate();
        BeanUtils.copyProperties(source,target);
        target.setAvailable(true);
        target.setId(null);
        templateDao.save(target);
        return CouponTemplateConverter.convertToTemplateInfo(target);
    }

    @Override
    public PagedCouponTemplateInfo search(TemplateSearchParams request) {
        CouponTemplate example = CouponTemplate.builder()
                .shopId(request.getShopId())
                .category(CouponType.convert(request.getType()))
                .available(request.getAvailable())
                .name(request.getName())
                .build();
        Pageable page = PageRequest.of(request.getPage(), request.getPageSize());
        Page<CouponTemplate> result = templateDao.findAll(Example.of(example),page);
        System.out.println(Example.of(example));
        List<CouponTemplateInfo> couponTemplateInfos = result.get().map(CouponTemplateConverter::convertToTemplateInfo).collect(Collectors.toList());
        PagedCouponTemplateInfo response = PagedCouponTemplateInfo.builder()
                .templates(couponTemplateInfos)
                .page(request.getPage())
                .total(result.getTotalElements())
                .build();
        return response;
    }

    @Override
    public CouponTemplateInfo loadTemplateInfo(Long templateId) {
        Optional<CouponTemplate> template = templateDao.findById(templateId);
        return template.isPresent()?CouponTemplateConverter.convertToTemplateInfo(template.get()):null;
    }

    @Override
    public void deleteTemplate(Long id) {
        int rows = templateDao.makeCouponUnavailable(id);
        if(rows == 0){
            throw new IllegalArgumentException("Template Not Found: "+id);
        }
    }

    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        List<CouponTemplate> templates = templateDao.findAllById(ids);
        return templates.stream().map(CouponTemplateConverter::convertToTemplateInfo).collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }
}
