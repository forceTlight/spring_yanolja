package com.yanolja.repository.rent

class RentSql {
    public static final String INSERT = """
			INSERT INTO rent (roomContentId, openTime, closeTime, maxTime, price, count, deleteYN)
			values (:roomContentId, :openTime, :closeTime, :maxTime, :price, :count, :deleteYN)
			""";
    public static final String SELECT = """
			SELECT * from rent where rentId = :rentId
			""";

    public static final String UPDATE = """
			UPDATE rent SET openTime = :openTime, closeTime = :closeTime, maxTime = :maxTime, price = :price, count = :count 
			WHERE lodgeId = :lodgeId
            """;
    public static final String DELETE = """
			UPDATE rent SET deleteYN = 'Y' WHERE rentId = :rentId
            """;
}
