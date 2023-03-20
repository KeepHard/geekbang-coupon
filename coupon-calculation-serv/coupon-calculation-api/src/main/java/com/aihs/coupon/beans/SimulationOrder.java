package com.aihs.coupon.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationOrder {
    @NotEmpty
    private List<Product> products;

    @NotEmpty
    private List<Long> couponIds;

    private List<CouponInfo> couponInfos;

    @NotNull
    private Long userId;
}
