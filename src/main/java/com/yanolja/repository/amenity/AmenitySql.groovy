package com.yanolja.repository.amenity

class AmenitySql {
    public static final String INSERT = """
			INSERT INTO amenity (roomId, amenityType, deleteYN)
			values (:roomId, :amenityType, :deleteYN)
			""";
    public static final String SELECT = """
			SELECT * from amenity where amenityId = :amenityId
			""";

    public static final String UPDATE = """
			UPDATE amenity SET roomId = :roomId, amenityType = :amenityType
			WHERE amenityId = :amenityId
            """;
    public static final String DELETE = """
			UPDATE amenity SET deleteYN = 'Y' WHERE amenityId = :amenityId
            """;
}
