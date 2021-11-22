package com.yanolja.repository.room

class RoomSql {
    public static final String INSERT = """
			INSERT INTO room (ownerId, roomType, name, phoneNumber, location, imgUrl, businessNumber, service, information, deleteYN)
			values (:ownerId, :roomType, :name, :phoneNumber, :location, :imgUrl, :businessNumber, :service, :information, :deleteYN)
			""";
    public static final String FIND_BY_NAME = """
			SELECT * from room where name like :name
			""";

    public static final String UPDATE = """
			UPDATE room SET roomType = :roomType, name = :name, phoneNumber = :phoneNumber, location = :location, ImgUrl = :ImgUrl,
			businessNumber = :businessNumber, service = :service, information = :information WHERE roomId = :roomId
            """;
    public static final String DELETE = """
			UPDATE room SET deleteYN = 'Y' WHERE roomId = :roomId
            """;

    // 총 게시글 갯수 출력
    public static final String COUNT_ROOM ="""
        SELECT COUNT(*) as count from room
    """

    // 페이징 처리 후 숙소 조회
    public static final String PAIGING_ROOM = """
        SELECT * from room where deleteYN = 'N' order by roomId DESC
        limit pageStart = :pageStart, perPageNum = :perPageNum 
        """;
}