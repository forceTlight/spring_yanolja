package com.yanolja.repository.room

class RoomSql {
    public static final String INSERT = """
			INSERT INTO room (ownerId, roomType, name, phoneNumber, location, ImgUrl, businessNumber, service, information, deleteYN)
			values (:ownerId, :roomType, :name, :phoneNumber, :location, :ImgUrl, : businessNumber, :service, :information, :deleteYN)
			""";
    public static final String SELECT = """
			SELECT * from room where roomId = :roomId
			""";

    public static final String UPDATE = """
			UPDATE room SET roomType = :roomType, name = :name, phoneNumber = :phoneNumber, location = :location, ImgUrl = :ImgUrl,
			businessNumber = :businessNumber, service = :service, information = :information WHERE roomId = :roomId
            """;
    public static final String DELETE = """
			UPDATE room SET deleteYN = 'Y' WHERE roomId = :roomId
            """;
}
