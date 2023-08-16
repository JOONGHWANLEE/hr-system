package com.hrsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrsystem.entity.User;


public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom{
	User findByNo(String no);
	
	
//	@Query(value="DELETE FROM user WHERE user_id = ':userId'", nativeQuery=true)
//	public void deleteUserIdFromUserWithUserId(@Param("userId") String userId);
}
