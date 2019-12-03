package com.bill.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.Dto.PaymentDTO;
import com.bill.model.Payment;
import com.bill.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public void save(Payment payment) {
	
		paymentRepository.save(payment);
	}

	@Override
	public List<PaymentDTO> getPaymentDetailsByGroupNameAndMemberName(String groupname, String membername) {
		
		List<Payment> paymentList=paymentRepository.getPaymentDetailsByGroupNameAndMemberName(groupname, membername);
		List<PaymentDTO> PaymentDTOList=new ArrayList<PaymentDTO>();
		paymentList.forEach(payment->{
			PaymentDTO paymentDTO=new  PaymentDTO();
			BeanUtils.copyProperties(payment, paymentDTO);
			PaymentDTOList.add(paymentDTO);
			});
		return PaymentDTOList;
	}

	@Override
	public List<PaymentDTO> getPaymentDetailsByGroupName(String groupname) {
		
		List<Payment> paymentList=paymentRepository.getPaymentDetailsByGroupName(groupname);
		List<PaymentDTO> PaymentDTOList=new ArrayList<PaymentDTO>();
		paymentList.forEach(payment->{
			PaymentDTO paymentDTO=new  PaymentDTO();
			BeanUtils.copyProperties(payment, paymentDTO);
			PaymentDTOList.add(paymentDTO);
			});
		return PaymentDTOList;
		
	}

}
