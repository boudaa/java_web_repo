package com.ensah.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensah.core.services.IPersonService;
import com.ensah.core.services.IUserService;
import com.ensah.core.web.models.AccountModel;

@Controller
@RequestMapping("/admin") // Very important because, in ou Spring security configuration,
							// URLs that start with ADMIN are dedicated to to the role admin.
							// All the URLs defined in this controller are
							// accessible only to the administrator
public class AccountController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IPersonService personService;

	// This method initializes the account creation form
	@RequestMapping(value = "createAccountForm/{idPerson}", method = RequestMethod.GET)
	public String createAccountForm(@PathVariable int idPerson, Model model) {

		model.addAttribute("accountModel", new AccountModel(Long.valueOf(idPerson)));
		model.addAttribute("roleList", userService.getAllRoles());
		model.addAttribute("accountList", userService.getAllAccounts());
		return "admin/formAccount";
	}

	// This method shows the list fo existing accounts
	@GetMapping("manageAccounts")
	public String manageAccounts(@ModelAttribute("accountModel") AccountModel accountModel, Model model) {

		model.addAttribute("accountList", userService.getAllAccounts());

		return "admin/accountList";
	}

	// this method displays the list of registered persons. The administrator can
	// then select a person to create an account for him/her
	@GetMapping("createAccounts")
	public String createAccounts(@ModelAttribute("accountModel") AccountModel accountModel, Model model) {

		model.addAttribute("personList", personService.getAllPersons());

		return "admin/accountCreation";
	}

	// This method adds the person to database
	@PostMapping("addAccount")
	public String addAccount(@ModelAttribute("accountModel") AccountModel accountModel, Model model) {

		// The account creation is implemented at service level
		// Just pass the role id and the person id to the service layer
		String password = userService.createUser(accountModel.getRoleId(), accountModel.getPersonId());

		// The password will be displayed in the view
		accountModel.setPassword(password);

		// The list of accounts is also displayed in the view
		model.addAttribute("accountList", userService.getAllAccounts());

		return "/admin/accountList";

	}

}
