package com.bill.service;

import java.util.List;

import com.bill.Dto.PaymentDTO;
import com.bill.model.Payment;

public interface IPaymentService {

	public void save(Payment payment);

	List<PaymentDTO> getPaymentDetailsByGroupNameAndMemberName(String groupname, String membername);

	List<PaymentDTO> getPaymentDetailsByGroupName(String groupname);
}
