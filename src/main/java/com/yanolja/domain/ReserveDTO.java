package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 숙소 예약 클래스
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveDTO {
    private int reserveId;
    private int userId;
    private int roomId;
    //이용 날짜
    private String visitDate;
    private String closeDate;
    //예약자 정보
    private String reserveName; // 예약자 이름
    private String reservePhoneNumber; //예약자  핸드폰 번호
    //이용자 정보
    private String useName; // 이용자 정보
    private String usePhoneNumber; // 이용자 휴대폰번호
    private String visitMethod; // 숙소 방문 수단

    private String deleteYN;
}
