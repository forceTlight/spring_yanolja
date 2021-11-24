package com.yanolja.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
객실 할인정보를 담고있는 클래스
fisrtComeYN == 'Y' (선착순)일 경우 int count 활성화
 */

public class Discount {
    @NoArgsConstructor
    @Data
    public static class Info{
        private int discountId;
        private Integer lodgeId;
        private Integer rentId;
        private int discountNum; // 할인해주는 가격
        private int count; // 선착순 쿠폰 갯수
        private String firstComeYN; // 선착순
    }
    // 할인권 등록
    @Builder
    @Data
    public static class RegisterReq{
        private int discountId;
        private Integer lodgeId;
        private Integer rentId;
        private int discountNum;
        private int count;
        private String firstComeYN;
    }

    @Builder
    @Data
    public static class findReq{
        private Integer discountId;
    }
    @Builder
    @Data
    public static class PatchReq{
        private int discountId;
        private Integer lodgeId;
        private Integer rentId;
        private int discountNum; // 할인해주는 가격
        private int count; // 선착순 쿠폰 갯수
        private String firstComeYN; // 선착순
    }
    @Builder
    @Data
    public static class deleteReq{
        private Integer discountId;
    }
}
