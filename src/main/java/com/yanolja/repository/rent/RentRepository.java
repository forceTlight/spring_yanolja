package com.yanolja.repository.rent;

import com.yanolja.domain.Rent;
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
public class RentRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // Rent 레코드 추가
    public Rent.RegisterReq insert(Rent.RegisterReq rent) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomContentId", rent.getRoomContentId())
                .addValue("openTime", rent.getOpenTime())
                .addValue("closeTime", rent.getCloseTime())
                .addValue("maxTime", rent.getMaxTime())
                .addValue("price", rent.getPrice())
                .addValue("count", rent.getCount())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(RentSql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        rent.setRentId(keyHolder.getKey().intValue());
        return rent;
    }
    // rentId를 사용해 레코드 업데이트
    public Integer updateById(Rent.PatchReq rent) {
        String qry = RentSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("rentId", rent.getRentId())
                .addValue("openTime", rent.getOpenTime())
                .addValue("closeTime", rent.getCloseTime())
                .addValue("maxTime", rent.getMaxTime())
                .addValue("price", rent.getPrice())
                .addValue("count", rent.getCount());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // deleteYN N -> Y
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("rentId", id);
        return namedParameterJdbcTemplate.update(RentSql.DELETE, parameterSource);
    }
    // rentId로 RentDTO 반환
    public Rent.Info findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("rentId", id);
        return namedParameterJdbcTemplate.queryForObject(RentSql.SELECT, parameterSource,
                new RentRepository.rentMapper());
    }
    // roomContentId로 RentDTO 반환
    public Rent.Info findByRoomContentId(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomContentId", id);
        return namedParameterJdbcTemplate.queryForObject(RentSql.FINDBYROOMCONTENTID, parameterSource,
                new RentRepository.rentMapper());
    }
    // rentId로 roomContentId 반환
    public int findRoomContentIdByRentId(Integer id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("rentId", id);
        return namedParameterJdbcTemplate.queryForObject(RentSql.FIND_ROOMCONTENTID_BY_RENTID, parameterSource, (rs, rowNum) -> {
           return rs.getInt("roomContentId");
        });
    }

    // queryForObject 수행시 Object 리턴해주기 위한 클래스
    private static final class rentMapper implements RowMapper<Rent.Info> {
        @Override
        public Rent.Info mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rent.Info rent = new Rent.Info();
            rent.setRentId(rs.getInt("rentId"));
            rent.setRoomContentId(rs.getInt("roomContentId"));
            rent.setOpenTime(rs.getString("openTime"));
            rent.setCloseTime(rs.getString("closeTime"));
            rent.setMaxTime(rs.getInt("maxTime"));
            rent.setPrice(rs.getInt("price"));
            rent.setCount(rs.getInt("count"));
            return rent;
        }
    }

}
