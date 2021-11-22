package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
public class Owner {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Info {
        private int ownerId;
        private String name;
        private String email;
        private String password;
        private String phoneNumber;
        private String deleteYN;
    }
    // 회원가입 REQUEST DTO
    @Data
    public static class RegisterReq{
        private String name;
        private String email;
        private String password;
        private String phoneNumber;
    }
    // 회원가입 RESPONSE DTO
    @Data
    @Builder
    public static class RegisterRes{
        private String name;
    }
    // 로그인 REQUEST DTO
    @Data
    public static class LoginReq{
        private String email;
        private String password;
    }
    // 로그인 RESPONSE DTO
    @Data
    @Builder
    public static class LoginRes{
        private int ownerId;
    }
    // 닉네임 수정 REQUEST DTO
    @Data
    @NoArgsConstructor
    public static class NameReq{
        private String name;
    }
    // 닉네임 수정 RESPONSE DTO
    @Data
    @Builder
    public static class PatchReq{
        private int ownerId;
        private String name;
    }
}
