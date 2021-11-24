package com.yanolja.repository.roomContent;

import com.yanolja.domain.RoomContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class RoomContentRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RoomContentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    // RoomContent 레코드 추가
    public RoomContent.RegisterReq insert(RoomContent.RegisterReq roomContent) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomId", roomContent.getRoomId())
                .addValue("imgUrl", roomContent.getImgUrl())
                .addValue("name", roomContent.getName())
                .addValue("content", roomContent.getContent())
                .addValue("count", roomContent.getCount())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(RoomContentSql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        roomContent.setRoomContentId(keyHolder.getKey().intValue());
        return roomContent;
    }
    // roomContentId를 사용해 레코드 업데이트
    public Integer updateById(RoomContent.PatchReq roomContent) {
        String qry = RoomContentSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomContentId", roomContent.getRoomContentId())
                .addValue("roomId", roomContent.getRoomId())
                .addValue("imgUrl", roomContent.getImgUrl())
                .addValue("name", roomContent.getName())
                .addValue("content", roomContent.getContent())
                .addValue("count", roomContent.getCount());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // deleteYN N -> Y
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomContentId", id);
        return namedParameterJdbcTemplate.update(RoomContentSql.DELETE, parameterSource);
    }
    // roomId(FK)에 따른 roomContent List 반환
    public List<RoomContent.Info> findByRoomId(Integer id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomId", id);
        return namedParameterJdbcTemplate.query(RoomContentSql.FINDBYROOMID, parameterSource, new roomContentMapper());
    }

    // roomContentId로 RoomContentDTO 반환
    public RoomContent.Info findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomContentId", id);
        return namedParameterJdbcTemplate.queryForObject(RoomContentSql.SELECT, parameterSource,
                new roomContentMapper());
    }
    // roomContentId로 roomId 반환
    public int findRoomIdByRoomContentId(Integer id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomContentId",id);
        return namedParameterJdbcTemplate.queryForObject(RoomContentSql.FIND_ROOMID, parameterSource, (rs, rowNum) -> {
               return rs.getInt("roomId");
        });
    }

    // queryForObject 수행시 Object 리턴해주기 위한 클래스
    private static final class roomContentMapper implements RowMapper<RoomContent.Info> {
        @Override
        public RoomContent.Info mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoomContent.Info roomContent = new RoomContent.Info();
            roomContent.setRoomContentId(rs.getInt("roomContentId"));
            roomContent.setRoomId(rs.getInt("roomId"));
            roomContent.setImgUrl(rs.getString("imgUrl"));
            roomContent.setName(rs.getString("name"));
            roomContent.setContent(rs.getString("content"));
            roomContent.setCount(rs.getInt("count"));
            roomContent.setImgUrl(rs.getString("ImgUrl"));
            return roomContent;
        }
    }
}
