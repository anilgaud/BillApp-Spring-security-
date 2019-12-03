package com.bill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bill.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query(value = "select pay from Payment pay where pay.groupname=:groupname AND pay.membername=:membername order by pay.id desc ")
	List<Payment> getPaymentDetailsByGroupNameAndMemberName(@Param("groupname") String groupname,
			@Param("membername") String membername);

	@Query("select pay from Payment pay where pay.groupname=:groupname")
	List<Payment> getPaymentDetailsByGroupName(@Param("groupname") String groupname);

}
