package com.ensah.core.services;

import java.util.List;

import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Person;
import com.ensah.core.bo.Role;
import com.ensah.core.bo.UserAccount;
import com.ensah.core.dao.IPersonDao;
import com.ensah.core.dao.IRoleDao;
import com.ensah.core.dao.IUserAccountDao;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserAccountDao userDao;

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IPersonDao personDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Role> getAllRoles() {
		return roleDao.findAll();
	}

	public List<UserAccount> getAllAccounts() {
		return userDao.findAll();
	}

	public String createUser(Long idRole, Long idPerson) {

		// get the person by id from the database
		Person person = personDao.getById(idPerson);

		// create an account
		UserAccount userAccount = new UserAccount();

		// set the owner
		userAccount.setPerson(person);

		// set the role
		userAccount.setRole(roleDao.getById(idRole));

		// generate a random password
		String generatedPass = generatePassayPassword();

		// password encoding
		String encodedPass = passwordEncoder.encode(generatedPass);

		// set the password
		userAccount.setPassword(encodedPass);

		UserAccount tempAccount = userDao.getUserByUsername(person.getEmail());

		if (tempAccount == null) {

			// the login is the email. the unicity is managed also at the database level
			userAccount.setUsername(person.getEmail());

			// Créer le compte
			userDao.save(userAccount);
			return generatedPass;
		}

		int i = 0;

		// else create a login = email+number
		String login ="";
		while (true) {

			login = person.getEmail() + i;
			tempAccount = userDao.getUserByUsername(login);
			if (tempAccount == null) {
				userAccount.setUsername(login);
                break;
			}

			i++;
		}

		// create the account
		userDao.save(userAccount); //throws an exception if username exists
		// return the generated password
		return generatedPass;

	}

	// generates random passwors using Passay
	public String generatePassayPassword() {
		CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);

		PasswordGenerator passwordGenerator = new PasswordGenerator();
		String password = passwordGenerator.generatePassword(10, digits);

		return password;
	}

}
