package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 숙박 정보를 담고있는 클래스
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LodgeDTO {
    private int lodgeId;
    private int roomContentId;
    private String checkIn;
    private String checkOut;
    private int price;
    private String deleteYN;
}
