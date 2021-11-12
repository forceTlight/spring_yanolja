package com.yanolja.repository.amenity;

import com.yanolja.domain.AmenityDTO;
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
public class AmenityRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AmenityRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    // Amenity 레코드 추가
    public AmenityDTO insert(AmenityDTO amenity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource("roomId", amenity.getRoomId())
                .addValue("amenityType", amenity.getAmenityType())
                .addValue("deleteYN", "N");
        int affectedRows = namedParameterJdbcTemplate.update(AmenitySql.INSERT, parameterSource, keyHolder);
        log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
        amenity.setAmenityId(keyHolder.getKey().intValue());
        return amenity;
    }
    // amenityId를 사용해 레코드 업데이트
    public Integer updateById(AmenityDTO amenity) {
        String qry = AmenitySql.UPDATE;
        SqlParameterSource parameterSource = new MapSqlParameterSource("amenityType", amenity.getAmenityType());
        return namedParameterJdbcTemplate.update(qry, parameterSource);
    }
    // deleteYN N -> Y
    public Integer deleteById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("amenityId", id);
        return namedParameterJdbcTemplate.update(AmenitySql.DELETE, parameterSource);
    }
    // amenityId로 AmenityDTO 반환
    public AmenityDTO findById(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("amenityId", id);
        return namedParameterJdbcTemplate.queryForObject(AmenitySql.SELECT, parameterSource,
                new AmenityRepository.amenityMapper());
    }
    // queryForObject 수행시 Object 리턴해주기 위한 클래스
    private static final class amenityMapper implements RowMapper<AmenityDTO> {
        @Override
        public AmenityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            AmenityDTO amenity = new AmenityDTO();
            amenity.setAmenityId(rs.getInt("amenityId"));
            amenity.setRoomId(rs.getInt("roomId"));
            amenity.setAmenityType(rs.getString("amenityType"));
            amenity.setDeleteYN(rs.getString("deleteYN"));
            return amenity;
        }
    }
}
