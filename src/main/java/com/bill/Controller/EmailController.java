package com.bill.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import com.bill.Dto.GroupDTO;
import com.bill.Dto.PaymentDTO;
import com.bill.model.Group;
import com.bill.service.IGroupService;
import com.bill.service.IPaymentService;
import com.bill.service.IUserService;

@Controller
public class EmailController {

	private static final Logger logger = LogManager.getLogger(EmailController.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	IGroupService iGroupService;

	@Autowired
	IUserService iUserService;

	@Autowired
	IPaymentService iPymentService;

	// send email
	void sendEmail(String email, String title, String message) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject(title);
		msg.setText(message);
		javaMailSender.send(msg);

		System.out.println("sending mail......");
	}

	// Send email notification when group is created
	void sendAmountMailToGroupMember(Group group) {
		GroupDTO group1 = iGroupService.getGroupDetailsByGroupName(group.getGroupname());
		String membername[] = group1.getMembername().split("[, ]+");// storing each member from group.membername

		for (String name : membername) {
			String msg = " Hey, " + name + " new Group " + group.getGroupname() + " is created\nThank You";

			String title = "Notification from " + group.getGroupname() + " Group";

			String email = iUserService.getEmailByName(name);

			Optional<String> chkNullEmail = Optional.ofNullable(email);
			if (chkNullEmail.isPresent()) {
				sendEmail(email, title, msg);
			}
		}
	}

	// Will do Amount calculation and Send Email notification, when any user will
	// pay amount to group
	public void calculationAndEmail(String groupname, String membername) {

		List<PaymentDTO> paymentlist = iPymentService.getPaymentDetailsByGroupName(groupname);
		HashMap<String, Double> usernameAndAmount = new HashMap<>();
		HashMap<String, HashMap<String, Double>> groupnameUsernameAndAmount = new HashMap<>();

		for (PaymentDTO payment : paymentlist) {
			usernameAndAmount.put(payment.getMembername(), payment.getAmount());
			groupnameUsernameAndAmount.put(payment.getGroupname(), usernameAndAmount);
		}

		GroupDTO group = iGroupService.getGroupDetailsByGroupName(groupname);
		String membernames[] = group.getMembername().split("[, ]+");
		int countMember = membernames.length;
		System.out.println("Count " + countMember);

		HashMap<String, Double> membersAndAmounts = new HashMap<>();
		for (String memberName : membernames) {

			// Storing a member who paid as well as not paid
			for (HashMap<String, Double> groupUserAndAmount : groupnameUsernameAndAmount.values()) {

				if (groupUserAndAmount.get(memberName) == null) {
					membersAndAmounts.put(memberName, 0.0);
				} else {
					membersAndAmounts.put(memberName, groupUserAndAmount.get(memberName));
				}

			}
		}

		double totalGroupAmount = membersAndAmounts.values().stream().reduce(0.0, Double::sum); // make the total of
																								// amount, of group
		System.out.println("totalGroupAmount:::" + totalGroupAmount);
		double eachMemberAmount = totalGroupAmount / countMember;
		for (String member : membernames) {
			if (membersAndAmounts.get(member) != null) {
				if (membersAndAmounts.get(member) == 0.0) {
					for (String member1 : membernames) {
						if (membersAndAmounts.get(member1) != null) {
							if (membersAndAmounts.get(member1) >= eachMemberAmount) {
								double memberAmount = membersAndAmounts.get(member1) - eachMemberAmount;

								if (memberAmount == eachMemberAmount) {
									membersAndAmounts.remove(member1);
									membersAndAmounts.remove(member);
									System.out.println("Sending amount email....1");
									emailFormat(memberAmount, member, member1, groupname);

									break;

								} else if (memberAmount < eachMemberAmount) {
									membersAndAmounts.put(member, memberAmount + eachMemberAmount);
									membersAndAmounts.remove(member1);
									System.out.println("Sending amount email....2");
									emailFormat(memberAmount, member, member1, groupname);

								}

								else {
									System.out.println("Sending amount email....5");
									emailFormat(eachMemberAmount, member, member1, groupname);
									membersAndAmounts.remove(member);
									membersAndAmounts.put(member1, memberAmount + eachMemberAmount);

								}

							}
						}
					}

				} else if (membersAndAmounts.get(member) <= eachMemberAmount) {
					for (String member3 : membernames) {
						if (membersAndAmounts.get(member3) != null && membersAndAmounts.get(member) != null) {

							if (membersAndAmounts.get(member3) > eachMemberAmount) {

								double member3Amount = membersAndAmounts.get(member3) - eachMemberAmount;
								double memberAmt = eachMemberAmount - membersAndAmounts.get(member);
								if (member3Amount > memberAmt) {
									System.out.println("Sending amount email....3");
									emailFormat(memberAmt, member, member3, groupname);
									membersAndAmounts.remove(member);
									membersAndAmounts.put(member3, member3Amount + eachMemberAmount);

								} else if (member3Amount == memberAmt) {
									emailFormat(memberAmt, member, member3, groupname);
									membersAndAmounts.remove(member);
									membersAndAmounts.remove(member3);

								} else {
									System.out.println("Sending amount email....4");
									emailFormat(memberAmt - member3Amount, member, member3, groupname);
									membersAndAmounts.remove(member3);
									membersAndAmounts.put(member, (memberAmt - member3Amount) + eachMemberAmount);

								}

							}

						}

					}

				}

			}
		}

	}

	void emailFormat(double amount, String whoPay, String whomToPay, String groupname) {
		String msg = " Hey, " + whoPay + " you have to pay " + amount + " Rs. to  " + whomToPay + " in " + groupname
				+ " Group\nThank You";

		String title = "Notification from " + groupname + " Group";

		String email = iUserService.getEmailByName(whoPay);

		Optional<String> chkNullEmail = Optional.ofNullable(email);
		if (chkNullEmail.isPresent())
			sendEmail(email, title, msg);

	}

}
