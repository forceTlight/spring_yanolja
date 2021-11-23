package com.yanolja.configuration;

public class ResponseMessage {
    /*
        User, Owner Message
     */
    public static final String EXIST_USER = "이미 존재하는 이메일입니다.";
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String SEARCH_USER = "회원 정보 조회 성공";
    public static final String SEARCH_USER_ERROR = "회원 정보 조회 실패";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String NOT_MATCHING_PASSWORD = "비밀번호가 틀립니다.";
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
     RoomContent Message
     */
    public static final String ROOMCONTENT_REGISTER_OK = "객실이 성공적으로 등록되었습니다.";
    public static final String ROOMCONTENT_FIND_OK = "객실 검색 성공";
    public static final String ROOMCONTENT_FINDBYROOMID_OK = "숙소에 대한 객실 불러오기 성공";
    public static final String ROOMCONTENT_UPDATE_OK = "객실 업데이트 성공";
    public static final String ROOMCONTENT_DELETE_OK = "객실 삭제 성공";

    public static final String ROOMCONTENT_REGISTER_ERROR = "객실 등록을 하는 도중 문제가 발생했습니다.";
    public static final String ROOMCONTENT_FIND_ERROR = "객실 검색 성공";
    public static final String ROOMCONTENT_FINDBYROOMID_ERROR = "숙소에 대한 객실 불러오는데 오류가 발생했습니다.";
    public static final String ROOMCONTENT_UPDATE_ERROR = "객실 업데이트를 하는 도중 오류가 발생했습니다.";
    public static final String ROOMCONTENT_DELETE_ERROR = "객실 삭제를 하는 도중 오류가 발생했습니다.";
    /*
     Lodge Message
     */
    public static final String LODGE_REGISTER_OK = "숙박정보가 성공적으로 등록되었습니다.";
    public static final String LODGE_FIND_OK = "숙박정보 검색 성공";
    public static final String LODGE_FINDBYROOMCONTENTID_OK = "객실에 대한 숙박정보 불러오기 성공";
    public static final String LODGE_UPDATE_OK = "숙박정보 업데이트 성공";
    public static final String LODGE_DELETE_OK = "숙박정보 삭제 성공";

    public static final String LODGE_REGISTER_ERROR = "숙박정보 등록을 하는 도중 문제가 발생했습니다.";
    public static final String LODGE_FIND_ERROR = "숙박정보 검색 성공";
    public static final String LODGE_FINDBYROOMCONTENTID_ERROR = "숙박정보에 대한 객실 불러오는데 오류가 발생했습니다.";
    public static final String LODGE_UPDATE_ERROR = "숙박정보 업데이트를 하는 도중 오류가 발생했습니다.";
    public static final String LODGE_DELETE_ERROR = "숙박정보 삭제를 하는 도중 오류가 발생했습니다.";
    /*
    Rent Message
     */
    public static final String RENT_REGISTER_OK = "대실정보가 성공적으로 등록되었습니다.";
    public static final String RENT_FIND_OK = "대실정보 검색 성공";
    public static final String RENT_FINDBYROOMCONTENTID_OK = "객실에 대한 숙박정보 불러오기 성공";
    public static final String RENT_UPDATE_OK = "대실정보 업데이트 성공";
    public static final String RENT_DELETE_OK = "대실정보 삭제 성공";

    public static final String RENT_REGISTER_ERROR = "대실정보 등록을 하는 도중 문제가 발생했습니다.";
    public static final String RENT_FIND_ERROR = "대실정보 검색 성공";
    public static final String RENT_FINDBYROOMCONTENTID_ERROR = "객실에 대한 대실정보를 불러오는데 오류가 발생했습니다.";
    public static final String RENT_UPDATE_ERROR = "대실정보 업데이트를 하는 도중 오류가 발생했습니다.";
    public static final String RENT_DELETE_ERROR = "대실정보 삭제를 하는 도중 오류가 발생했습니다.";
    /*
        DataBase Message
     */
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
    /*
    JWT MESSAGE
     */
    public static final String EMPTY_JWT = "JWT 토큰이 비었습니다.";
    public static final String INVALID_JWT = "유효하지 않은 JWT 토큰입니다.";
}
