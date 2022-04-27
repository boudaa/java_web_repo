package com.ensah.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * Custom implementation of AuthenticationFailureHandler, used to customize the
 * behavior of the Spring Security Framework after an authentication Failure
 * 
 * @author T. BOUDAA
 *
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		// write your custom code here

		if (exception instanceof DisabledException) {
			response.sendRedirect("showMyLoginPage?error=disabled");
			return;

		}

		else if (exception instanceof LockedException) {
			response.sendRedirect("showMyLoginPage?error=locked");
			return;

		} else if (exception instanceof CredentialsExpiredException || exception instanceof AccountExpiredException) {
			response.sendRedirect("showMyLoginPage?error=expired");
			return;

		}else if (exception instanceof DisabledException) {
			response.sendRedirect("showMyLoginPage?error=disabled");
			return;

		} else {
			response.sendRedirect("showMyLoginPage?error=other");

		}
	}

}