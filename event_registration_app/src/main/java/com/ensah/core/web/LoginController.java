package com.ensah.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showLoginForm() {

		return "loginForm";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}

	@GetMapping("/user/showUserHome")
	public String showUserHomePage() {

		return "user/userHomePage";

	}

	@GetMapping("/admin/showAdminHome")
	public String showAdminHome() {

		return "admin/adminHome";

	}

}
