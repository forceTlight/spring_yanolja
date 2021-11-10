package com.yanolja.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnerDTO {
    private int ownerId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String deleteYN;
}
