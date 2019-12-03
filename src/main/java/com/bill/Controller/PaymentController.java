package com.bill.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bill.Dto.GroupDTO;
import com.bill.Dto.PaymentDTO;
import com.bill.model.Group;
import com.bill.model.Payment;
import com.bill.service.IGroupService;
import com.bill.service.IPaymentService;

@Controller
public class PaymentController {

	private static final Logger logger = LogManager.getLogger(PaymentController.class);

	@Autowired
	IGroupService igroupService;

	@Autowired
	private Payment payment;

	@Autowired
	IPaymentService iPymentService;

	@Autowired
	private EmailController emailController;

	@Autowired
	BillController billController;

	// When user click on perticular group
	@RequestMapping("/pay")
	public ModelAndView pay() {

		ModelAndView mav = new ModelAndView();
		List<GroupDTO> grouplist = igroupService.getAllList();
		List<GroupDTO> grplist = new ArrayList<>();
		String username = billController.getUserName();
		for (GroupDTO group : grouplist) {
			if (group.getMembername().toString().contains(username)) {
				grplist.add(group);
			}
		}
		mav.setViewName("payment");
		mav.addObject("group", grplist);
		mav.addObject("username", username);
		return mav;
	}

	// payment for single group
	@RequestMapping("/singlePay")
	public ModelAndView siglePay(@RequestParam("grpid") Long id) {
		Group group = igroupService.getgroupById(id);

		List<Payment> paylist = new ArrayList<>();
		String membernames[] = group.getMembername().split("[, ]+");
		for (String name : membernames) {
			Payment payment = new Payment();
			payment.setGroupname(group.getGroupname());
			payment.setMembername(name);
			try {
				PaymentDTO pay = iPymentService.getPaymentDetailsByGroupNameAndMemberName(group.getGroupname(), name)
						.get(0);

				Optional<PaymentDTO> chkNullPayment = Optional.ofNullable(pay);
				if (chkNullPayment.isPresent())
					payment.setAmount(pay.getAmount());
			} catch (IndexOutOfBoundsException e) {

			}
			paylist.add(payment);
		}

		String username = billController.getUserName();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("singlePay");
		mav.addObject("paymentlist", paylist);
		mav.addObject("groupname", paylist.get(0).getGroupname());
		mav.addObject("payment", payment);
		mav.addObject("username", username);
		return mav;
	}

	// Save Data when user pay to the group
	@RequestMapping(value = "/doPaymentSingleGroup", method = RequestMethod.POST)
	public ModelAndView doPaymentSingleGroup(HttpServletRequest httpServletRequest) {

		String amount = httpServletRequest.getParameter("amount");
		System.out.println("amount: " + amount);
		String membername = httpServletRequest.getParameter("membername");
		System.out.println("membername: " + membername);
		String groupname = httpServletRequest.getParameter("groupname");
		System.out.println("groupname " + groupname);

		Payment payment = new Payment();
		payment.setAmount(Double.valueOf(amount));
		payment.setMembername(membername);
		payment.setGroupname(groupname);

		iPymentService.save(payment);

		emailController.calculationAndEmail(groupname, membername);// Will do amount calculation and notify user via
																	// email
		ModelAndView mav = new ModelAndView();
		mav.setViewName("welcome");
		mav.addObject("username", billController.getUserName());
		return mav;
	}

}
