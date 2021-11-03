package com.yanolja.repository

class UserSql {
	public static final String INSERT = """
			INSERT INTO user (name, profileImgUrl, email, password, phoneNumber, deleteYN)
			values (:name, :profileImgUrl, :email, :password, :phoneNumber, :deleteYN)
			""";
			
	public static final String UPDATE = """
			UPDATE user SET NAME = :name, profileImgUrl = :profileImgUrl, email = :email,
			password = :password, phoneNumber = :phoneNumber
""";
	public static final String ID_CONDITION = """
			AND userId = :userId
""";
	public static final String DELETE = """
			DELETE FROM user WHERE 1=1
"""
}
