package com.guptha.gateway.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guptha.gateway.models.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	UserInfo findByEmail(String userEmail);

	Optional<UserInfo> findByName(String username);

}
