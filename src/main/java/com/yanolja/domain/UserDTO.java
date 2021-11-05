package com.yanolja.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
