package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Neo
 * 2021.11.01
 * 유저의 정보를 담고있는 DTO
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	private int userId;
	private String name;
	private String profileImgUrl;
	private String email;
	private String password;
	private String phoneNumber;
	private String deleteYN;
	/*private LocalDateTime insertTime;
	private LocalDateTime updateTime;
	private LocalDateTime deleteTime;*/
}
