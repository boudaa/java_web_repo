package com.ensah.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//This controller displays the index page 
@Controller
public class InitiController {
	@RequestMapping("/")
	public String index(Model model) {

		return "index";
	}
}
