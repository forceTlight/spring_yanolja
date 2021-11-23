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

}
