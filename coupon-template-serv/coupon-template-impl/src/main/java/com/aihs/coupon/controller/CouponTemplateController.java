package com.aihs.coupon.controller;

import com.aihs.coupon.beans.CouponTemplateInfo;
import com.aihs.coupon.beans.PagedCouponTemplateInfo;
import com.aihs.coupon.beans.TemplateSearchParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aihs.coupon.service.intf.CouponTemplateService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/template")
public class CouponTemplateController {
    @Autowired
    private CouponTemplateService templateService;

    @PostMapping("/addTemplate")
    public CouponTemplateInfo addTemplate(@Valid @RequestBody CouponTemplateInfo request){
        log.info("Create coupon template: data={} ",request);
        return templateService.createTemplate(request);
    }

    @PostMapping("/cloneTemplate")
    public CouponTemplateInfo cloneTemplate(@RequestParam("id") Long id){
        log.info("clone coupon template: data={}",id);
        return templateService.cloneTemplate(id);
    }

    @GetMapping("/getTemplate")
    public CouponTemplateInfo getTemplate(@RequestParam("id") Long id){
        log.info("Load template,id={}",id);
        return templateService.loadTemplateInfo(id);
    }

    @GetMapping("/getBatch")
    public Map<Long,CouponTemplateInfo> getTemplateBatch(@RequestParam("ids") Collection<Long> ids){
        log.info("getTemplateBatch ids:{}",ids);
        return templateService.getTemplateInfoMap(ids);
    }

    @PostMapping("/search")
    public PagedCouponTemplateInfo search(@Valid @RequestBody TemplateSearchParams request){
        log.info("search templates,payload:{}",request);
        return templateService.search(request);
    }

    @DeleteMapping("/deleteTemplate")
    public void deleteTemplate(@RequestParam("id") Long id){
        log.info("delete template,id:{}",id);
        templateService.deleteTemplate(id);
    }


}
