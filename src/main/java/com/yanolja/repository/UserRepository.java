package com.yanolja.repository;

import com.yanolja.domain.UserDTO;
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
public class UserRepository implements UserRepositoryInterface{
private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	UserSql userSql;
	public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	// User 레코드 추가
	public UserDTO insert(UserDTO user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new MapSqlParameterSource("name", user.getName())
				.addValue("profileImgUrl", user.getProfileImgUrl())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("phoneNumber", user.getPhoneNumber())
				.addValue("deleteYN", "N");
		int affectedRows = namedParameterJdbcTemplate.update(userSql.INSERT, parameterSource, keyHolder);
		log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
		user.setUserId(keyHolder.getKey().intValue());
		return user;
	}
	// UserId를 사용해 레코드 업데이트
	public Integer updateById(UserDTO user) {
		String qry = UserSql.UPDATE;
		SqlParameterSource parameterSource = new MapSqlParameterSource("userId", user.getUserId())
				.addValue("name", user.getName())
				.addValue("profileImgUrl", user.getProfileImgUrl())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("phoneNumber", user.getPhoneNumber());
		return namedParameterJdbcTemplate.update(qry, parameterSource);
	}
	// UserId를 사용해 User 레코드 제거
	public Integer deleteById(Integer id) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("userId", id);
		return namedParameterJdbcTemplate.update(UserSql.DELETE, parameterSource);
	}
	// UserId를 사용해 User 찾기
	public UserDTO findById(Integer id) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("userId", id);
		return namedParameterJdbcTemplate.queryForObject(UserSql.SELECT, parameterSource,
				new UserMapper());
	}
	// queryForObject 수행시 User 리턴해주기 위한 클래스
	private static final class UserMapper implements RowMapper<UserDTO>{
		@Override
		public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDTO user = new UserDTO();
			user.setUserId(rs.getInt("userId"));
			user.setEmail(rs.getString("email"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setPhoneNumber(rs.getString("phoneNumber"));
			user.setDeleteYN(rs.getString("deleteYN"));
			user.setProfileImgUrl(rs.getString("profileImgUrl"));
			return user;
		}
	}
}
