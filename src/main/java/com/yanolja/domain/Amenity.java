package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Room 의 편의정보(AmenitiesEnum)의 정보를 가지고있는 클래스
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Amenity {
    private int amenityId;
    private int roomId;
    private String amenityType; // 편의시설 정보
    private String deleteYN;
}
