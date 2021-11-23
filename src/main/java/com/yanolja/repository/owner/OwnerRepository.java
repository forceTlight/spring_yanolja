package com.yanolja.repository.owner;

import com.yanolja.domain.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class OwnerRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    OwnerSql ownerSql;
    public OwnerRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    // Owner 레코드 추가
    public Owner.RegisterRes insert(Owner.RegisterReq owner) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", owner.getName())
                .addValue("email", owner.getEmail())
                .addValue("nickName", owner.getNickName())
                .addValue("password", owner.getPassword())
                .addValue("phoneNumber", owner.getPhoneNumber())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(ownerSql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        return Owner.RegisterRes.builder().name(owner.getName()).build();
    }
    // OwnerId를 사용해 레코드 업데이트
    public Integer updateById(Owner.PatchReq owner) {
        String qry = OwnerSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("ownerId", owner.getOwnerId())
                .addValue("nickName", owner.getNickName());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // OwnerId를 사용해 Owner 레코드 제거
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("ownerId", id);
        return namedParameterJdbcTemplate.update(OwnerSql.DELETE, parameterSource);
    }
    // deleteYN N -> Y
    public Owner.Info findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("ownerId", id);
        return namedParameterJdbcTemplate.queryForObject(OwnerSql.SELECT, parameterSource,
                new OwnerRepository.OwnerMapper());
    }

    public Owner.Info findByEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
        try {
            return namedParameterJdbcTemplate.queryForObject(OwnerSql.FIND_BY_EMAIL, parameterSource, new OwnerMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    // queryForObject 수행시 Owner 리턴해주기 위한 클래스
    private static final class OwnerMapper implements RowMapper<Owner.Info>{
        @Override
        public Owner.Info mapRow(ResultSet rs, int rowNum) throws SQLException {
            Owner.Info owner = new Owner.Info();
            owner.setOwnerId(rs.getInt("ownerId"));
            owner.setEmail(rs.getString("email"));
            owner.setName(rs.getString("name"));
            owner.setNickName(rs.getString("nickName"));
            owner.setPassword(rs.getString("password"));
            owner.setPhoneNumber(rs.getString("phoneNumber"));
            owner.setDeleteYN(rs.getString("deleteYN"));
            return owner;
        }
    }
}
