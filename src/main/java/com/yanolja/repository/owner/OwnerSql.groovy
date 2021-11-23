package com.yanolja.repository.owner

class OwnerSql{
    public static final String INSERT = """
			INSERT INTO owner (name, nickName, email, password, phoneNumber, deleteYN)
			values (:name, :nickName, :email, :password, :phoneNumber, :deleteYN)
			""";
    public static final String SELECT = """
			SELECT * from owner where ownerId = :ownerId
			""";

    public static final String UPDATE = """
			UPDATE owner SET nickName = :nickName WHERE ownerId = :ownerId
""";
    public static final String DELETE = """
			UPDATE owner SET deleteYN = 'Y' WHERE ownerId = :ownerId
""";
    public static final String FIND_BY_EMAIL = """
			SELECT * from owner where email = :email
"""
}
