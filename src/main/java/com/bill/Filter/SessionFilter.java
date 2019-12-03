package com.bill.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.bill.model.User;

//@Component
public class SessionFilter implements Filter {

	@Autowired
	User user;

	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			String page = ((HttpServletRequest) request).getRequestURI();
			System.out.println("page is " + page);
			if (page.endsWith("/login") || page.endsWith("/") || page.endsWith("/register")
					|| page.endsWith("/RegisterPage")) {
				System.out.println("--------------------");
				chain.doFilter(request, response);
			} else {
				System.out.println(";;;;;;;;;;;;;;;;;;;");
				// User user = (User) ((HttpServletRequest)
				// request).getSession().getAttribute("User");
				String username;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserDetails) {
					username = ((UserDetails) principal).getUsername();
				} else {
					username = principal.toString();
				}
				System.out.println("user session:::" + username);
				if (username == null || username.equals("anonymousUser")) {
					((HttpServletResponse) response).sendRedirect("login");
				} else {
					chain.doFilter(request, response);
				}

			}

		} catch (Exception ex) {

		}

	}

}
