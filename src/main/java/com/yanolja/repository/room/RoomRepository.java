package com.yanolja.repository.room;

import com.yanolja.domain.Room;
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
public class RoomRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RoomRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // Room 레코드 추가
    public Room.RegisterReq insert(Room.RegisterReq room) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", room.getName())
                .addValue("ownerId", room.getOwnerId())
                .addValue("roomType", room.getRoomType())
                .addValue("name", room.getName())
                .addValue("phoneNumber", room.getPhoneNumber())
                .addValue("location", room.getLocation())
                .addValue("imgUrl", room.getImgUrl())
                .addValue("businessNumber", room.getBusinessNumber())
                .addValue("service", room.getService())
                .addValue("information", room.getInformation())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(RoomSql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        //room.setRoomId(keyHolder.getKey().intValue());
        return room;
    }
    // roomId를 사용해 레코드 업데이트
    public Integer updateById(Room.PatchRoomReq room) {
        String qry = RoomSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomId", room.getRoomId())
                .addValue("roomType", room.getRoomType())
                .addValue("name", room.getName())
                .addValue("phoneNumber", room.getPhoneNumber())
                .addValue("location", room.getLocation())
                .addValue("imgUrl", room.getImgUrl())
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
    // roomName으로 RoomDTO 반환
    public List<Room.Info> findByName(String name) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", "%"+name+"%");
        return namedParameterJdbcTemplate.query(RoomSql.FIND_BY_NAME,parameterSource, new roomMapper());
    }
    /* // 페이징 (임시 주석처리)
    public List<Room.Info> paigingRoom(int pageStart, int perPageNum){
        SqlParameterSource parameterSource = new MapSqlParameterSource("pageStart", pageStart)
                .addValue("perPageNum", perPageNum);
        return namedParameterJdbcTemplate.query(RoomSql.PAIGING_BOARD, parameterSource, new roomMapper());
    }
    public int countRoom(){
        return namedParameterJdbcTemplate.query(RoomSql.COUNT_ROOM, rs -> {
           return rs.getInt("count");
        });
    }

    public List<Room.Info> paigingRoom(PagingVO vo){
        vo.get
        return namedParameterJdbcTemplate.
    }*/
    // RoomDTO LIST 반환해주기 위한 클래스
    private static final class roomMapper implements RowMapper<Room.Info> {
        @Override
        public Room.Info mapRow(ResultSet rs, int rowNum) throws SQLException {
                Room.Info room = new Room.Info();
                room.setRoomId(rs.getInt("roomId"));
                room.setOwnerId(rs.getInt("ownerId"));
                room.setRoomType(rs.getString("roomType"));
                room.setName(rs.getString("name"));
                room.setPhoneNumber(rs.getString("phoneNumber"));
                room.setLocation(rs.getString("location"));
                room.setImgUrl(rs.getString("imgUrl"));
                room.setBusinessNumber(rs.getString("businessNumber"));
                room.setService(rs.getString("service"));
                room.setInformation(rs.getString("information"));
                return room;
        }
    }
}
