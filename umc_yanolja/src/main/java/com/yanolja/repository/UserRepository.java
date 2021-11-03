package com.yanolja.repository;

import org.apache.catalina.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.yanolja.domain.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepository implements UserRepositoryInterface{
private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public UserDTO insert(UserDTO user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new MapSqlParameterSource("name", user.getName())
				.addValue("profileImgUrl", user.getProfileImgUrl())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("phoneNumber", user.getPhoneNumber())
				.addValue("deleteYN", "N");
		int affectedRows = namedParameterJdbcTemplate.update(UserSql.INSERT, parameterSource, keyHolder);
		log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKeys());
		user.setUserId(keyHolder.getKey().intValue());
		return user;
	}
	
	public Integer updateById(UserDTO user) {
		String qry = UserSql.UPDATE + UserSql.ID_CONDITION;
		SqlParameterSource parameterSource = new MapSqlParameterSource("userId", user.getUserId())
				.addValue("name", user.getName())
				.addValue("profileImgUrl", user.getProfileImgUrl())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("phoneNumber", user.getPhoneNumber());
		return namedParameterJdbcTemplate.update(qry, parameterSource);
	}
	public Integer deleteById(Integer id) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
		return namedParameterJdbcTemplate.update(UserSql.DELETE + UserSql.ID_CONDITION, parameterSource);
	}
}
