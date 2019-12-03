package com.bill.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bill.Dto.GroupDTO;
import com.bill.Reports.PDFBuilder;
import com.bill.model.Group;
import com.bill.service.IGroupService;

@Controller
public class GroupController {

	private static final Logger logger = LogManager.getLogger(GroupController.class);

	@Autowired
	private Group group;

	@Autowired
	IGroupService igroupService;

	@Autowired
	private EmailController emailController;

	@Autowired
	private BillController billController;

	// create group
	@RequestMapping("/createGroup")
	public ModelAndView createGroup() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("createGroup");
		mav.addObject("username", billController.getUserName());
		mav.addObject("group", group);
		return mav;
	}

	// save created group data in database
	@RequestMapping(value = "/saveGroup", method = RequestMethod.POST)
	public ModelAndView saveGroup(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult) {

		String username = billController.getUserName();
		group.setMembername(group.getMembername() + "," + username);// whan user create group,automatically add username
																	// of that user
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors())
			return new ModelAndView("createGroup", "username", username);
		if (group.getId() == 0)
			igroupService.save(group);
		else
			igroupService.updateGroup(group);
		emailController.sendAmountMailToGroupMember(group);
		mav.setViewName("welcome");
		mav.addObject("userName", username);
		mav.addObject("message", "group saved successfully");
		return mav;
	}

	// show group list
	@RequestMapping("/myGroup")
	public ModelAndView myGroup() {
		List<GroupDTO> grouplist = igroupService.getAllList();
		String username = billController.getUserName();
		System.out.println("my group username " + username);
		List<GroupDTO> usernameGroupList = grouplist.stream().filter(x -> x.getMembername().contains(username))
				.collect(Collectors.toList());// create seperate list of group,in which logged in user is part
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mygroup");
		mav.addObject("grouplist", usernameGroupList);
		mav.addObject("username", username);
		return mav;
	}

	// Will delete Group
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
	public ModelAndView deleteGroup(@RequestParam("grpid") int grpid) {
		igroupService.deleteGroup(grpid);
		ModelAndView mav = new ModelAndView("redirect:/myGroup");
		return mav;
	}

	// For edit in group
	@RequestMapping(value = "/editGroup", method = RequestMethod.GET)
	public ModelAndView editGroup(@RequestParam("grpid") int grpid) {
		ModelAndView mav = new ModelAndView("createGroup");
		Group group = igroupService.getgroupById(grpid);
		mav.addObject("group", group);
		mav.addObject("grpid", grpid);
		return mav;
	}

	
	@RequestMapping("/downloadGroupPdf")
	public ModelAndView downloadGroupPdf() {
		List<GroupDTO> groupList = igroupService.getAllList();
		return new ModelAndView(new PDFBuilder(), "groupList", groupList);
	}

}
