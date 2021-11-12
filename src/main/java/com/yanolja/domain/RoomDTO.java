package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class RoomDTO {
    // 유저 기본정보
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private int roomId;
        private int ownerId;
        private String roomType;
        private String name;
        private String phoneNumber;
        private String location;
        private String ImgUrl;
        private String businessNumber;
        private String service; // 편의시설 및 서비스 내용을 담고있는 변수
        private String information; // 이용안내 내용을 담고있는 변수 !->bold, ','->줄바꿈
        private String deleteYN;
    }
    // 숙소 등록 REQUEST DTO
    @Data
    public static class RegisterReq{
        private int ownerId;
        private String roomType;
        private String name;
        private String phoneNumber;
        private String location;
        private String ImgUrl;
        private String businessNumber;
        private String service;
        private String information;
    }
    // 숙소 이름으로 검색 REQUEST DTO
    @Data
    public static class FindNameReq{
        private String name;
    }
    // 숙소 이름으로 검색 RESPONSE DTO
    @Data
    @Builder
    public static class FindNameRes{
        private List<Info> roomList;
    }
    // 숙소 정보 수정 REQUEST DTO
    @Data
    @Builder
    public static class PatchReq{
        private int roomId;
        private String roomType;
        private String name;
        private String phoneNumber;
        private String location;
        private String ImgUrl;
        private String businessNumber;
        private String service;
        private String information;
    }
}
