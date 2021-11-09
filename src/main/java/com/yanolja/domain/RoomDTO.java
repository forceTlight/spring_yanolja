package com.yanolja.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {
    private int roomId;
    private int ownerId;
    private String roomType;
    private String name;
    private String phoneNumber;
    private String location;
    private String ImgUrl;
    private String businessNumber;
    private String service; // 편의시설 및 서비스 내용을 담고있는 변수
    private String information; // 이용안내 내용을 담고있는 변수 !->bold, ','->줄바꿈
    private String deleteYN;
}
