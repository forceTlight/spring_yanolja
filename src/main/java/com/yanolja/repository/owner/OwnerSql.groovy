package com.yanolja.repository.owner

class OwnerSql{
    public static final String INSERT = """
                INSERT INTO user (name, email, password, phoneNumber, deleteYN)
                values (:name, :email, :password, :phoneNumber, :deleteYN)
                """;
    public static final String SELECT = """
                SELECT * from user where ownerId = :ownerId
                """;

    public static final String UPDATE = """
                UPDATE owner SET name = :name, email = :email, password = :password
                ,phoneNumber = :phoneNumber WHERE ownerId = :ownerId
    """;
    public static final String DELETE = """
                UPDATE owner SET deleteYN = 'Y' WHERE ownerId = :ownerId
    """;
}
