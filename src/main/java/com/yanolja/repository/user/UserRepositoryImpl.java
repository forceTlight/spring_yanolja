package com.yanolja.repository.user;

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
public class UserRepositoryImpl implements UserRepository {
private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	UserSql userSql;
	public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	// User 레코드 추가
	public UserDTO.RegisterRes insert(UserDTO.RegisterReq user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new MapSqlParameterSource("name", user.getName())
				.addValue("profileImgUrl", user.getProfileImgUrl())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("phoneNumber", user.getPhoneNumber())
				.addValue("deleteYN", "N");
		int affectedRows = namedParameterJdbcTemplate.update(userSql.INSERT, parameterSource, keyHolder);
		log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
		return UserDTO.RegisterRes.builder().name(user.getName()).build();
	}
	// UserId를 사용해 레코드 업데이트
	public Integer updateById(UserDTO.PatchReq user) {
		String qry = UserSql.UPDATE;
		SqlParameterSource parameterSource = new MapSqlParameterSource("userId", user.getUserId())
				.addValue("name", user.getName());
		return namedParameterJdbcTemplate.update(qry, parameterSource);
	}
	// UserId를 사용해 User 레코드 제거
	public Integer deleteById(Integer id) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("userId", id);
		return namedParameterJdbcTemplate.update(UserSql.DELETE, parameterSource);
	}
	// deleteYN N -> Y
	public UserDTO.Info findById(Integer id) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("userId", id);
		return namedParameterJdbcTemplate.queryForObject(UserSql.SELECT, parameterSource,
				new UserMapper());
	}

	public UserDTO.Info findByEmail(String email) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
		return namedParameterJdbcTemplate.queryForObject(UserSql.FIND_BY_EMAIL, parameterSource, new UserMapper());
	}

	// queryForObject 수행시 User 리턴해주기 위한 클래스
	private static final class UserMapper implements RowMapper<UserDTO.Info>{
		@Override
		public UserDTO.Info mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDTO.Info user = new UserDTO.Info();
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
