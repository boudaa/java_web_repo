package com.ensah.security;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.UserAccount;
import com.ensah.core.dao.IUserAccountDao;

/**
 * A custom implementation of UserDetailsService
 * 
 * @author T. BOUDAA
 *
 */
@Service
@Transactional
public class CustomDaoBasedUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserAccountDao userRepository; // the repository that manages users

	/** Utilisé pour la journalisation */
	private Logger LOGGER = Logger.getLogger(getClass().getName());

	// Implementation of the method to check the existence of an account
	// with the login passed in parameter
	@Override
	public UserDetails loadUserByUsername(String username) {

		UserAccount user = null;

		// We get the account with its username
		user = userRepository.getUserByUsername(username);

		// If not exists
		if (user == null) {
			String msg = "Utilisateur " + username + " n'ayant aucune autorisation ";
			// A warning message is written in the log file
			LOGGER.warn(msg);

			// This exception informs Spring security that the user does not exist
			// and therefore is not allowed to log in
			throw new UsernameNotFoundException(msg);
		}
		if (user.getRole() == null) {
			String msg = "Utilisateur " + username + " n'ayant aucune autorisation ";
			// A warning message is written in the log file
			LOGGER.warn(msg);

			// This exception informs Spring that the user has no authorization
			throw new UsernameNotFoundException(msg);
		}

		// Wrap the object of type UserAccount in an object of type UserPrincipal
		// which itself implements UserDetails.
		return new UserPrincipal(user);
	}

}