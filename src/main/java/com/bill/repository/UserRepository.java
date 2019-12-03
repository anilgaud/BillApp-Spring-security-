package com.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bill.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@Query("select user.email from User user where user.name=:name")
	String getEmailByName(@Param("name") String name);

	@Query("from User user where user.username=:username")
	User getUserByUsername(@Param("username") String username);
}
