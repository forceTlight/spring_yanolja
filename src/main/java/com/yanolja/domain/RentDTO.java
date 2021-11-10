package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
대실 정보를 담고있는 클래스
RoomType.Motel 에서만 사용함(나머지 사용 X)
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentDTO {
    private int rentId;
    private int roomContentId;
    private String openTime;
    private String closeTime;
    private int maxTime; // 대실 최대 이용시간
    private int price;
    private int count; // 잔여 객실
    private String deleteYN;
}
