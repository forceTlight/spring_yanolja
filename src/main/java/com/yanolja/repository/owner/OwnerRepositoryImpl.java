package com.yanolja.repository.owner;

import com.yanolja.domain.OwnerDTO;
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
public class OwnerRepositoryImpl implements OwnerRepository{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OwnerRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    // Owner 레코드 추가
    public OwnerDTO insert(OwnerDTO owner) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", owner.getName())
                .addValue("email", owner.getEmail())
                .addValue("password", owner.getPassword())
                .addValue("phoneNumber", owner.getPhoneNumber())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(OwnerSql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        owner.setOwnerId(keyHolder.getKey().intValue());
        return owner;
    }
    // ownerId를 사용해 레코드 업데이트
    public Integer updateById(OwnerDTO owner) {
        String qry = OwnerSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("ownerId", owner.getOwnerId())
                .addValue("name", owner.getName())
                .addValue("email", owner.getEmail())
                .addValue("password", owner.getPassword())
                .addValue("phoneNumber", owner.getPhoneNumber());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // deleteYN N -> Y
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("ownerId", id);
        return namedParameterJdbcTemplate.update(OwnerSql.DELETE, parameterSource);
    }
    // roomId로 RoomDTO 반환
    public OwnerDTO findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("ownerId", id);
        return namedParameterJdbcTemplate.queryForObject(OwnerSql.SELECT, parameterSource,
                new OwnerRepositoryImpl.ownerMapper());
    }
    // queryForObject 수행시 Object 리턴해주기 위한 클래스
    private static final class ownerMapper implements RowMapper<OwnerDTO> {
        @Override
        public OwnerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            OwnerDTO owner = new OwnerDTO();
            owner.setOwnerId(rs.getInt("ownerId"));
            owner.setEmail(rs.getString("email"));
            owner.setName(rs.getString("name"));
            owner.setPassword(rs.getString("password"));
            owner.setPhoneNumber(rs.getString("phoneNumber"));
            owner.setDeleteYN(rs.getString("deleteYN"));
            return owner;
        }
    }
}
