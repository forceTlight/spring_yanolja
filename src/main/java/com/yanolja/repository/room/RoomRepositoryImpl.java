package com.yanolja.repository.room;

import com.yanolja.domain.RoomDTO;
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

@Slf4j
@Repository
public class RoomRepositoryImpl {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RoomRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // Room 레코드 추가
    public RoomDTO insert(RoomDTO room) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", room.getName())
                .addValue("ownerId", room.getOwnerId())
                .addValue("roomType", room.getRoomType())
                .addValue("name", room.getName())
                .addValue("phoneNumber", room.getPhoneNumber())
                .addValue("location", room.getLocation())
                .addValue("ImgUrl", room.getImgUrl())
                .addValue("businessNumber", room.getBusinessNumber())
                .addValue("service", room.getService())
                .addValue("information", room.getInformation())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(RoomSql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        room.setRoomId(keyHolder.getKey().intValue());
        return room;
    }
    // roomId를 사용해 레코드 업데이트
    public Integer updateById(RoomDTO room) {
        String qry = RoomSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomId", room.getRoomId())
                .addValue("roomType", room.getRoomType())
                .addValue("name", room.getName())
                .addValue("phoneNumber", room.getPhoneNumber())
                .addValue("location", room.getLocation())
                .addValue("ImgUrl", room.getImgUrl())
                .addValue("businessNumber", room.getBusinessNumber())
                .addValue("service", room.getService())
                .addValue("information", room.getInformation());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // deleteYN N -> Y
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomId", id);
        return namedParameterJdbcTemplate.update(RoomSql.DELETE, parameterSource);
    }
    // roomId로 RoomDTO 반환
    public RoomDTO findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomId", id);
        return namedParameterJdbcTemplate.queryForObject(RoomSql.SELECT, parameterSource,
                new RoomRepositoryImpl.roomMapper());
    }
    // queryForObject 수행시 Object 리턴해주기 위한 클래스
    private static final class roomMapper implements RowMapper<RoomDTO> {
        @Override
        public RoomDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoomDTO room = new RoomDTO();
            room.setRoomId(rs.getInt("roomId"));
            room.setOwnerId(rs.getInt("ownerId"));
            room.setRoomType(rs.getString("roomType"));
            room.setName(rs.getString("name"));
            room.setPhoneNumber(rs.getString("phoneNumber"));
            room.setLocation(rs.getString("location"));
            room.setImgUrl(rs.getString("ImgUrl"));
            room.setBusinessNumber(rs.getString("businessNumber"));
            room.setService(rs.getString("service"));
            room.setInformation(rs.getString("information"));
            room.setDeleteYN(rs.getString("deleteYN"));
            return room;
        }
    }
}
