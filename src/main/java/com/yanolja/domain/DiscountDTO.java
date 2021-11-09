package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
객실 할인정보를 담고있는 클래스
fisrtComeYN == 'Y' (선착순)일 경우 int count 활성화
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountDTO {
    private int discountId;
    private int lodgeId;
    private int rentId;
    private int discoutNum; // 할인해주는 가격
    private int count; // 선착순 쿠폰 갯수
    private String firstComeYN; // 선착순
    private String deleteYN;
}
