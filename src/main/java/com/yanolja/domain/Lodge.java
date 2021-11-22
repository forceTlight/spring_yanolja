package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 숙박 정보를 담고있는 클래스
public class Lodge {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Info {
        private int lodgeId;
        private int roomContentId;
        private String checkIn;
        private String checkOut;
        private int price;
    }
    //숙박 등록 REQUEST DTO
    @Data
    public static class RegisterReq{
        private int lodgeId;
        private int roomContentId;
        private String checkIn;
        private String checkOut;
        private int price;
    }
    // 숙소 정보 수정 REQUEST DTO
    @Data
    @Builder
    public static class PatchReq{
        private int lodgeId;
        private int roomContentId;
        private String checkIn;
        private String checkOut;
        private int price;
    }
    // 숙소 아이디로 숙박 정보 불러오기
    // REQUEST DTO
    @Data
    @Builder
    public static class LoadLodgeReq {
        private int roomContentId;
    }
}
