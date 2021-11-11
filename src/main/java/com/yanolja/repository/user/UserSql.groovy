package com.yanolja.repository.user

class UserSql {
	public static final String INSERT = """
			INSERT INTO user (name, profileImgUrl, email, password, phoneNumber, deleteYN)
			values (:name, :profileImgUrl, :email, :password, :phoneNumber, :deleteYN)
			""";
	public static final String SELECT = """
			SELECT * from user where userId = :userId
			""";

	public static final String UPDATE = """
			UPDATE user SET name = :name WHERE userId = :userId
""";
	public static final String DELETE = """
			UPDATE user SET deleteYN = 'Y' WHERE userId = :userId
""";
	public static final String FIND_BY_EMAIL = """
			SELECT * from user where email = :email
"""
}
