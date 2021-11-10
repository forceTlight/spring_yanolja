package com.yanolja.repository.reserve

class ReserveSql {
    public static final String INSERT = """
			INSERT INTO reserve (userId, roomId, visitDate, closeDate, reserveName, reservePhoneNumber, useName, usePhoneNumber, visitMethod, deleteYN)
			values (:userId, :roomId, :visitDate, :closeDate, :reserveName, :reservePhoneNumber, : useName, :usePhoneNumber, :visitMethod, :deleteYN)
			""";
    public static final String SELECT = """
			SELECT * from reserve where reserveId = :reserveId
			""";

    public static final String UPDATE = """
			UPDATE reserve SET visitDate = :visitDate, closeDate = :closeDate, reserveName = :reserveName, reservePhoneNumber = :reservePhoneNumber, useName = :useName,
			usePhoneNumber = :usePhoneNumber, visitMethod = :visitMethod WHERE reserveId = :reserveId
            """;
    public static final String DELETE = """
			UPDATE reserve SET deleteYN = 'Y' WHERE reserveId = :reserveId
            """;
}
