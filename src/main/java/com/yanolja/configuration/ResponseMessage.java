package com.yanolja.configuration;

public class ResponseMessage {
    /*
        User, Owner Message
     */
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String ENCRYPT_ERROR = "암호화 하는 도중 오류가 발생했습니다.";
    public static final String DECRYPT_ERROR = "복호화 하는 도중 오류가 발생했습니다.";

    /*

     Room Message

    */
    public static final String ROOM_REGISTER_OK = "숙소가 성공적으로 등록되었습니다.";
    public static final String ROOM_FIND_OK = "숙소 검색 성공";
    public static final String ROOM_UPDATE_OK = "숙소 업데이트 성공";
    public static final String ROOM_DELETE_OK = "숙소 삭제 성공";

    public static final String ROOM_REGISTER_ERROR = "숙소 등록을 하는 도중 문제가 발생했습니다.";
    public static final String ROOM_FIND_ERROR = "숙소 검색을 하는 도중 문제가 발생했습니다.";
    public static final String ROOM_UPDATE_ERROR = "숙소 업데이트를 하는 도중 오류가 발생했습니다.";
    public static final String ROOM_DELETE_ERROR = "숙소를 삭제하는 도중 오류가 발생했습니다.";
     /*
        DataBase Message
     */
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
}
