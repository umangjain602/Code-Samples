package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
	
	List<UserEntity> findByUserName(String username);
	
}
