package com.ensah.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ensah.core.bo.Person;
import com.ensah.core.services.IPersonService;

@Controller
public class RegistrationController {

	@Autowired
	private IPersonService personService;

	@RequestMapping("/showForm")
	public String showForm(Model model) {

		model.addAttribute("personModel", new Person());
		return "registrationForm";
	}

	@RequestMapping("addPerson")
	public String process(@ModelAttribute("personModel") Person person, Model model) {

		personService.addPerson(person);
		model.addAttribute("msg", "registration is successful");
		return "registrationForm";
	}
}
