package com.yanolja.repository.discount;

import com.yanolja.domain.DiscountDTO;
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
public class DiscountRepositoryImpl {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DiscountRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    // Discount 레코드 추가
    public DiscountDTO insert(DiscountDTO discount) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("lodgeId", discount.getLodgeId())
                .addValue("rentId", discount.getRentId())
                .addValue("discountNum", discount.getDiscountNum())
                .addValue("count", discount.getCount())
                .addValue("firstComeYN", discount.getFirstComeYN())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(DiscountSql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        discount.setDiscountId(keyHolder.getKey().intValue());
        return discount;
    }
    // discountId를 사용해 레코드 업데이트
    public Integer updateById(DiscountDTO discount) {
        String qry = DiscountSql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("discountId", discount.getDiscountId())
                .addValue("discountNum", discount.getDiscountNum())
                .addValue("count", discount.getCount())
                .addValue("firstComeYN", discount.getFirstComeYN());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // deleteYN N -> Y
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("discountId", id);
        return namedParameterJdbcTemplate.update(DiscountSql.DELETE, parameterSource);
    }
    // discountId로 DiscountDTO 반환
    public DiscountDTO findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("discountId", id);
        return namedParameterJdbcTemplate.queryForObject(DiscountSql.SELECT, parameterSource,
                new DiscountRepositoryImpl.discountMapper());
    }
    // queryForObject 수행시 Object 리턴해주기 위한 클래스
    private static final class discountMapper implements RowMapper<DiscountDTO> {
        @Override
        public DiscountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            DiscountDTO discount = new DiscountDTO();
            discount.setDiscountId(rs.getInt("discountId"));
            discount.setLodgeId(rs.getInt("lodgeId"));
            discount.setRentId(rs.getInt("rentId"));
            discount.setDiscountNum(rs.getInt("discountNum"));
            discount.setCount(rs.getInt("count"));
            discount.setFirstComeYN(rs.getString("firstComeYN"));
            discount.setDeleteYN(rs.getString("deleteYN"));
            return discount;
        }
    }
}
