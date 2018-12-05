/*package com.aqryuz.footballTicketDemo.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aqryuz.footballTicketDemo.entity.UserEntity;
import com.aqryuz.footballTicketDemo.repository.UserRepository;

import io.jsondb.JsonDBTemplate;

@Repository
public class UserRepositoryImpl implements UserRepository{
	@Autowired
	private JsonDBTemplate jsonDBTemplate;
	@Override
	public List<UserEntity> findAll() {
		return jsonDBTemplate.findAll(UserEntity.class);
	}

	@Override
	public UserEntity findByName(String userName) {
		String jxQuery = String.format("/.[userName='%s']", userName);
		return jsonDBTemplate.findOne(jxQuery, UserEntity.class);

	}
}
*/