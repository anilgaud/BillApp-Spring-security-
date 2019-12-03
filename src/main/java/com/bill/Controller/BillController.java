package com.bill.Controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bill.model.User;
import com.bill.service.IUserService;

@Controller
public class BillController {

	private static final Logger logger = LogManager.getLogger(BillController.class);

	@Autowired
	IUserService iUserService;

	@Autowired
	private User user;

//	login page
	@RequestMapping(value = { "/", "/login"})
	public ModelAndView get(@RequestParam(required = false) String error) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		if (error != null)
			mav.addObject("error", "UserName or Password is invalid!!!");
		return mav;
	}

//	Registration Page
	@RequestMapping(value = "/registerPage")
	public ModelAndView registerPage() {
		return new ModelAndView("register", "user", user);
	}

	// save data in database
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors())
			return new ModelAndView("register");
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		iUserService.save(user);

		mav.setViewName("index");
		mav.addObject("Message", "Registration Done Suc" + "cessfully,Please Login");
		return mav;
	}

	// back button
	@RequestMapping("/back")
	public ModelAndView back() {

		return new ModelAndView("welcome", "userName", getUserName());

	}

	@RequestMapping("/welcome")
	public ModelAndView welcome() {

		return new ModelAndView("welcome", "userName", getUserName());
	}

	public String getUserName() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

}
