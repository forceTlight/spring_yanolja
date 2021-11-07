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
    private OwnerDTO owner;
    private String roomId;
    private String roomType;
    private String name;
    private String location;
    private String ImgUrl;
    private String businessNumber;
    private String checkIn;
    private String checkOut;
    private String deleteYN;
}
