package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//  Room의 객실을 담고있는 클래스
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomContentDTO {
    private int roomContentId;
    private int roomId;
    private String imgUrl;
    private String name;
    private String content;
    private int count; // 잔여 객실
    private String deleteYN;
}
