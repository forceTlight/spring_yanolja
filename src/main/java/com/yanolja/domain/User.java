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

public class User {
	// 유저 기본정보
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Info {
		private int userId;
		private String name;
		private String nickName;
		private String profileImgUrl;
		private String email;
		private String password;
		private String phoneNumber;
		private String deleteYN;
	}
	// 회원가입 REQUEST DTO
	@Data
	public static class RegisterReq{
		private String name;
		private String nickName;
		private String profileImgUrl;
		private String email;
		private String password;
		private String phoneNumber;
	}
	// 회원가입 RESPONSE DTO
	@Data
	@Builder
	public static class RegisterRes{
		private String name;
	}
	// 로그인 REQUEST DTO
	@Data
	public static class LoginReq{
		private String email;
		private String password;
	}
	// 로그인 RESPONSE DTO
	@Data
	@Builder
	public static class LoginRes{
		private int userId;
		private String email;
	}
	// 닉네임 수정 REQUEST DTO
	@Data
	@NoArgsConstructor
	public static class NameReq{
		private String nickName;
	}
	// 닉네임 수정 RESPONSE DTO
	@Data
	@Builder
	public static class PatchReq{
		private int userId;
		private String nickName;
	}
	/*private LocalDateTime insertTime;
	private LocalDateTime updateTime;
	private LocalDateTime deleteTime;*/
}
