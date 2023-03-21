package com.aihs.coupon.entity;

import com.aihs.coupon.beans.CouponTemplateInfo;
import com.aihs.coupon.convertor.CouponStatusConvertor;
import com.aihs.coupon.enums.CouponStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "template_id",nullable = false)
    private Long templateId;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "status",nullable = false)
    @Convert(converter = CouponStatusConvertor.class)
    private CouponStatus status;

    @Transient
    private CouponTemplateInfo templateInfo;

    @CreatedDate
    @Column(name = "created_time",nullable = false)
    private Date createdTime;

}
