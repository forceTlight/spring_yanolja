package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//  Room의 객실을 담고있는 클래스

public class RoomContent {
    // 객실 기본정보
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Info {
        private int roomContentId;
        private int roomId;
        private String imgUrl;
        private String name;
        private String content;
        private int count; // 잔여 객실
    }
    // 객실 등록 REQUEST DTO
    @Data
    public static class RegisterReq{
        private int roomContentId;
        private int roomId;
        private String imgUrl;
        private String name;
        private String content;
        private int count; // 잔여 객실
    }
    // 객실 정보 수정 REQUEST DTO
    @Data
    @Builder
    public static class PatchReq{
        private int roomContentId;
        private int roomId;
        private String imgUrl;
        private String name;
        private String content;
        private int count; // 잔여 객실
    }
}
