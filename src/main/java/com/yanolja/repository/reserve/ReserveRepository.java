package com.yanolja.repository.reserve;

import com.yanolja.domain.Reserve;
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
public class ReserveRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ReserveRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // Reserve 레코드 추가
    public Reserve insert(Reserve reserve) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("userId", reserve.getUserId())
                .addValue("roomId", reserve.getRoomId())
                .addValue("visitDate", reserve.getVisitDate())
                .addValue("closeDate", reserve.getCloseDate())
                .addValue("reserveName", reserve.getReserveName())
                .addValue("reservePhoneNumber", reserve.getReservePhoneNumber())
                .addValue("useName", reserve.getUseName())
                .addValue("usePhoneNumber", reserve.getUsePhoneNumber())
                .addValue("visitMethod", reserve.getVisitMethod())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(ReserveSql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        reserve.setReserveId(keyHolder.getKey().intValue());
        return reserve;
    }
    // reserveId를 사용해 레코드 업데이트
    public Integer updateById(Reserve reserve) {
        String qry = ReserveSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("reserveId", reserve.getReserveId())
                .addValue("visitDate", reserve.getVisitDate())
                .addValue("closeDate", reserve.getCloseDate())
                .addValue("reserveName", reserve.getReserveName())
                .addValue("reservePhoneNumber", reserve.getReservePhoneNumber())
                .addValue("useName", reserve.getUseName())
                .addValue("usePhoneNumber", reserve.getUsePhoneNumber())
                .addValue("visitMethod", reserve.getVisitMethod());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // deleteYN N -> Y
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("reserveId", id);
        return namedParameterJdbcTemplate.update(ReserveSql.DELETE, parameterSource);
    }
    // reserveId로 ReserveDTO 반환
    public Reserve findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("reserveId", id);
        return namedParameterJdbcTemplate.queryForObject(ReserveSql.SELECT, parameterSource,
                new ReserveRepository.reserveMapper());
    }
    // queryForObject 수행시 Object 리턴해주기 위한 클래스
    private static final class reserveMapper implements RowMapper<Reserve> {
        @Override
        public Reserve mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reserve reserve = new Reserve();
            reserve.setReserveId(rs.getInt("reserveId"));
            reserve.setUserId(rs.getInt("userId"));
            reserve.setVisitDate(rs.getString("visitDate"));
            reserve.setCloseDate(rs.getString("closeDate"));
            reserve.setReserveName(rs.getString("reserveName"));
            reserve.setReservePhoneNumber(rs.getString("reservePhoneNumber"));
            reserve.setUseName(rs.getString("useName"));
            reserve.setUsePhoneNumber(rs.getString("usePhoneNumber"));
            reserve.setVisitMethod(rs.getString("visitMethod"));
            reserve.setDeleteYN(rs.getString("deleteYN"));
            return reserve;
        }
    }
}
