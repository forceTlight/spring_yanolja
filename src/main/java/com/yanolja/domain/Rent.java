package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
대실 정보를 담고있는 클래스
RoomType.Motel 에서만 사용함(나머지 사용 X)
 */
public class Rent {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Info {
        private int rentId;
        private int roomContentId;
        private String openTime;
        private String closeTime;
        private int maxTime; // 대실 최대 이용시간
        private int price;
        private int count; // 잔여 객실
    }
    @Data
    public static class RegisterReq{
        private int rentId;
        private int roomContentId;
        private String openTime;
        private String closeTime;
        private int maxTime; // 대실 최대 이용시간
        private int price;
        private int count; // 잔여 객실
    }
    @Data
    public static class PatchReq{
        private int rentId;
        private int roomContentId;
        private String openTime;
        private String closeTime;
        private int maxTime; // 대실 최대 이용시간
        private int price;
        private int count; // 잔여 객실
    }
}
