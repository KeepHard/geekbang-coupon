package com.aihs.coupon.entity;

import com.aihs.coupon.beans.rules.TemplateRule;
import com.aihs.coupon.converter.CouponTypeConverter;
import com.aihs.coupon.converter.RuleConverter;
import com.aihs.coupon.enums.CouponType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coupon_template")
@EntityListeners(AuditingEntityListener.class)
public class CouponTemplate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "available",nullable = false)
    private Boolean available;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "description")
    private String description;

    @Column(name = "type",nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType category;

    @CreatedDate
    @Column(name = "created_time",nullable = false)
    private Date createdTime;

    @Column(name = "rule",nullable = false)
    @Convert(converter = RuleConverter.class)
    private TemplateRule rule;

}
