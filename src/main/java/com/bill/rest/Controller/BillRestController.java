
package com.bill.rest.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bill.Controller.BillController;
import com.bill.Controller.EmailController;
import com.bill.Dto.GroupDTO;
import com.bill.Dto.PaymentDTO;
import com.bill.Dto.UserDTO;
import com.bill.model.Group;
import com.bill.model.Payment;
import com.bill.model.User;
import com.bill.service.IGroupService;
import com.bill.service.IPaymentService;
import com.bill.service.IUserService;

@RestController
@RequestMapping(value = "/bill")
public class BillRestController {

	@Autowired
	IUserService iUserService;

	@Autowired
	IGroupService igroupService;

	@Autowired
	BillController billController;

	@Autowired
	IPaymentService iPymentService;

	@Autowired
	private EmailController emailController;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GroupDTO> getGroupDetailsAll() {
		return igroupService.getAllList();
	}

	@RequestMapping(value = "/getOne/{groupname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GroupDTO getOneGroupDetailsAll(@PathVariable("groupname") String groupname) {
		return igroupService.getGroupDetailsByGroupName(groupname);
	}

	@RequestMapping("/chkGroupByUsername")
	public int chkGroupByUsername() {
		List<GroupDTO> grouplist = igroupService.getAllList();
		String username = billController.getUserName();
		System.out.println("my group username " + username);
		List<GroupDTO> usernameGroupList = grouplist.stream().filter(x -> x.getMembername().contains(username))
				.collect(Collectors.toList());//
		if (usernameGroupList != null)
			return 1;
		else
			return 0;
	}

	@RequestMapping(value = "/getUser/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getUserDetailsByUserName(@PathVariable("username") String username) {
		return iUserService.getUserByUsername(username);
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveUser(@RequestBody UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		iUserService.save(user);
		return "Record saved successfully";
	}

	@RequestMapping(value = "/saveGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveUser(@RequestBody GroupDTO groupDTO) {
		Group group = new Group();
		BeanUtils.copyProperties(groupDTO, group);
		igroupService.save(group);
		return "Record saved successfully";
	}

	@RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String singleGroupPayment(@RequestBody PaymentDTO paymentDTO) {
		Payment payment = new Payment();
		BeanUtils.copyProperties(paymentDTO, payment);

		iPymentService.save(payment);

		emailController.calculationAndEmail(paymentDTO.getGroupname(), paymentDTO.getMembername());

		return "amount paid successfully";
	}

}
