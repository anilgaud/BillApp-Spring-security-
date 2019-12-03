package com.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bill.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	@Query("select gr from Group gr where gr.groupname=:groupname")
	Group getGroupDetailsByGroupName(@Param("groupname") String groupname);

	@Transactional
	@Modifying
	@Query("update Group u set u.groupname=:groupname ,u.membername=:membername  where u.id=:id ")
	void updateGroup(@Param("groupname") String groupname, @Param("membername") String membername,
			@Param("id") Long id);

}
