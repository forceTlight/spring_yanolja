package com.yanolja.repository.lodge;

import com.yanolja.domain.Lodge;
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
public class LodgeRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LodgeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    // Lodge 레코드 추가
    public Lodge.RegisterReq insert(Lodge.RegisterReq lodge) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomContentId", lodge.getRoomContentId())
                .addValue("checkIn", lodge.getCheckIn())
                .addValue("checkOut", lodge.getCheckOut())
                .addValue("price", lodge.getPrice())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(LodgeSql.INSERT, parameterSource, keyHolder);
        lodge.setLodgeId(keyHolder.getKey().intValue());
        return lodge;
    }
    // lodgeId를 사용해 레코드 업데이트
    public Integer updateById(Lodge.PatchReq lodge) {
        String qry = LodgeSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("lodgeId", lodge.getLodgeId())
                .addValue("checkIn", lodge.getCheckIn())
                .addValue("checkOut", lodge.getCheckOut())
                .addValue("price", lodge.getPrice());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // deleteYN N -> Y
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("lodgeId", id);
        return namedParameterJdbcTemplate.update(LodgeSql.DELETE, parameterSource);
    }
    // lodgeId로 LodgeDTO 반환
    public Lodge.Info findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("lodgeId", id);
        return namedParameterJdbcTemplate.queryForObject(LodgeSql.SELECT, parameterSource,
                new LodgeRepository.lodgeMapper());
    }
    // roomContentId로 LodgeDTO 반환
    public Lodge.Info findByRoomContentId(Integer id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomContentId", id);
        return namedParameterJdbcTemplate.queryForObject(LodgeSql.FINDBYROOMCONTENTID, parameterSource, new LodgeRepository.lodgeMapper());
    }
    // lodgeId로 roomContentId 반환
    public int findRoomContentIdByLodgeId(Integer id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("lodgeId",id);
        return namedParameterJdbcTemplate.queryForObject(LodgeSql.FIND_ROOMCONTENTID_BY_LODGEID,parameterSource, (rs, rowNum) -> {
           return rs.getInt("roomContentId");
        });
    }

    // queryForObject 수행시 Object 리턴해주기 위한 클래스
    private static final class lodgeMapper implements RowMapper<Lodge.Info> {
        @Override
        public Lodge.Info mapRow(ResultSet rs, int rowNum) throws SQLException {
            Lodge.Info lodge = new Lodge.Info();
            lodge.setLodgeId(rs.getInt("lodgeId"));
            lodge.setRoomContentId(rs.getInt("roomContentId"));
            lodge.setCheckIn(rs.getString("checkIn"));
            lodge.setCheckOut(rs.getString("checkOut"));
            lodge.setPrice(rs.getInt("price"));
            return lodge;
        }
    }
}
