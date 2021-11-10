package com.yanolja.repository.discount

class DiscountSql {
    public static final String INSERT = """
			INSERT INTO discount (lodgeId, rentId, discountNum, count, firstComeYN, deleteYN)
			values (:lodgeId, :rentId, :discountNum, :count, :firstComeYN, :deleteYN)
			""";
    public static final String SELECT = """
			SELECT * from discount where discountId = :discountId
			""";

    public static final String UPDATE = """
			UPDATE discount SET discountNum = :discountNum, count = :count, firstComeYN = :firstComeYN
			WHERE discountId = :discountId
            """;
    public static final String DELETE = """
			UPDATE discount SET deleteYN = 'Y' WHERE discountId = :discountId
            """;
}
