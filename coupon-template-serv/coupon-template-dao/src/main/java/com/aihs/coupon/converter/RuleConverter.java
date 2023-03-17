package com.aihs.coupon.converter;

import com.aihs.coupon.beans.rules.TemplateRule;
import com.alibaba.fastjson.JSON;

import javax.persistence.AttributeConverter;

public class RuleConverter implements AttributeConverter<TemplateRule,String> {
    @Override
    public String convertToDatabaseColumn(TemplateRule templateRule) {
        return JSON.toJSONString(templateRule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule,TemplateRule.class);
    }
}
