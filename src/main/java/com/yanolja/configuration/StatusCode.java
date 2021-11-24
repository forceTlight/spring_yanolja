package com.yanolja.configuration;

public class StatusCode {
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int NO_CONTENT = 204;
    public static final int BAD_REQUEST =  400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int DB_ERROR = 600;

    // JWT 에러 700~
    public static final int JWT_ERROR = 700;

    //로그인,회원가입,회원조회(1000~1009)
    public static final int LOGIN_FAIL = 1000;
    public static final int LOGIN_SUCCESS = 1001;
    public static final int REGISTER_FAIL = 1002;
    public static final int REGISTER_SUCCESS = 1003;
    public static final int SEARCH_SUCCESS = 1004;
    public static final int SEARCH_FAIL = 1005;
    public static final int EXIST_USER = 1006;

    //복호화, 암호화에러 (1010~1019)
    public static final int ENCRYPT_ERROR = 1010;
    public static final int DECRYPT_ERROR = 1011;

    // Room 생성, 검색, 수정, 삭제 (1020~1029)
    public static final int ROOM_REGISTER_SUCCESS = 1020;
    public static final int ROOM_REGISTER_FAIL = 1021;
    public static final int ROOM_SEARCH_SUCCESS = 1022;
    public static final int ROOM_SEARCH_FAIL = 1023;
    public static final int ROOM_UPDATE_SUCCESS = 1024;
    public static final int ROOM_UPDATE_FAIL = 1025;
    public static final int ROOM_DELETE_SUCCESS = 1026;
    public static final int ROOM_DELETE_FAIL = 1027;

    // Room Content 생성, 상세조회, 객실정보 모두 불러오기,객실 수정, 객실 삭제 (1030~1039)
    public static final int ROOMCONTENT_REGISTER_SUCCESS = 1030;
    public static final int ROOMCONTENT_REGISTER_FAIL = 1031;
    public static final int ROOMCONTENT_SEARCH_SUCCESS = 1032;
    public static final int ROOMCONTENT_SEARCH_FAIL = 1033;
    public static final int ROOMCONTENT_LOAD_SUCCESS = 1034;
    public static final int ROOMCONTENT_LOAD_FAIL = 1035;
    public static final int ROOMCONTENT_UPDATE_SUCCESS = 1036;
    public static final int ROOMCONTENT_UPDATE_FAIL = 1037;
    public static final int ROOMCONTENT_DELETE_SUCCESS = 1038;
    public static final int ROOMCONTENT_DELETE_FAIL = 1039;

    // Lodge 생성, 조회, 수정, 삭제 (1040~1049)
    public static final int LODGE_REGISTER_SUCCESS = 1040;
    public static final int LODGE_REGISTER_FAIL = 1041;
    public static final int LODGE_SEARCH_SUCCESS = 1042;
    public static final int LODGE_SEARCH_FAIL = 1043;
    public static final int LODGE_UPDATE_SUCCESS = 1044;
    public static final int LODGE_UPDATE_FAIL = 1045;
    public static final int LODGE_DELETE_SUCCESS = 1046;
    public static final int LODGE_DELETE_FAIL = 1047;

    // Discount 생성, 조회, 수정, 삭제 (1050~1059)
    public static final int DISCOUNT_REGISTER_SUCCESS = 1050;
    public static final int DISCOUNT_REGISTER_FAIL = 1051;
    public static final int DISCOUNT_SEARCH_SUCCESS = 1052;
    public static final int DISCOUNT_SEARCH_FAIL = 1053;
    public static final int DISCOUNT_UPDATE_SUCCESS = 1054;
    public static final int DISCOUNT_UPDATE_FAIL = 1055;
    public static final int DISCOUNT_DELETE_SUCCESS = 1056;
    public static final int DISCOUNT_DELETE_FAIL = 1057;
}
