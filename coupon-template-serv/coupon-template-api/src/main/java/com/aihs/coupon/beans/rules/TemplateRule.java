package com.aihs.coupon.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {
    /*
    * 可以享受的折扣
    * */
    private Discount discount;

    /*
    * 每人最多可领的张数
    * */
    private Integer limitation;

    /*
    * 过期时间
    * */
    private Long deadLine;
}
