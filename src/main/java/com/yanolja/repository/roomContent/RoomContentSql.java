package com.yanolja.repository.roomContent;

public class RoomContentSql {
    public static final String INSERT = """
			INSERT INTO roomContent (roomContentId, imgUrl, name, content, count, deleteYN)
			values (:roomContentId, :imgUrl, :name, :content, :count, :deleteYN)
			""";
    public static final String SELECT = """
			SELECT * from roomContent where roomContentId = :roomContentId
			""";

    public static final String UPDATE = """
			UPDATE roomContent SET imgUrl = :imgUrl, name = :name, content = :content, count = :count
			WHERE roomContentId = :roomContentId
            """;
    public static final String DELETE = """
			UPDATE roomContent SET deleteYN = 'Y' WHERE roomContentId = :roomContentId
            """;
	// roomId(FK)로 List<RoomContent> 반환
	public static final String FINDBYROOMID = """
			SELECT * from roomContent where roomId = :roomId
			""";
}
