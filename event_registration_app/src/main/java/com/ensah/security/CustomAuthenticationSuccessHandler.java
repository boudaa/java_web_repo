package com.ensah.security;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Custom implementation of AuthenticationSuccessHandler, used to customize the
 * behavior of the Spring Security Framework after successful authentication
 * 
 * @author T. BOUDAA
 *
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	// Logger
	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		String userHomePage = "";

		Map<String, String> homePageByRole = new HashMap<>();
		// The user of type USER will be redirected to "/user/showUserHome"
		homePageByRole.put("ROLE_USER", "/user/showUserHome");
		// The user of type ADMIN will be redirected to "/admin/showAdminHome"
		homePageByRole.put("ROLE_ADMIN", "/admin/showAdminHome");

		// We retrieve the user's roles, and according to his role we determine the
		// action to execute among the actions declared in homePageByRole
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		boolean found = false;
		for (GrantedAuthority grantedAuthority : authorities) {

			String authorityName = grantedAuthority.getAuthority();
			// if found
			if (homePageByRole.containsKey(authorityName)) {
				userHomePage = homePageByRole.get(authorityName);
				found = true;
				break;

			}
		}

		// If the user's role cannot be found in the dictionary homePageByRole
		if (!found)

		{
			throw new IllegalStateException();

		}

		// redirection to the home page
		
		if (response.isCommitted()) {
			LOGGER.debug("Impossible to redirect because the response has already been sent ");
			return;
		}

		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		redirectStrategy.sendRedirect(request, response, userHomePage);

	}

}
