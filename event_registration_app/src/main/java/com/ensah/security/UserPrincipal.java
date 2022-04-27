package com.ensah.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ensah.core.bo.UserAccount;

/**
 * The UserPrincipal class represents a user account in our application. This
 * class implements the UserDetails interface to meet the Spring Security
 * specifications.
 * 
 * @author T. BOUDAA
 *
 */
public class UserPrincipal implements UserDetails {
	private UserAccount user; // The user account (persistent class to be managed by the ORM)

	public UserPrincipal(UserAccount user) {
		this.user = user;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	/**
	 * 
	 * This method defines the user's authorizations. In our case, we use the notion
	 * of role as a set of authorizations, so we will return the only role of the
	 * user in the form of a collection containing a single object of type
	 * GrantedAuthority to respect the specifications of this method.
	 * 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> arrayAuths = new ArrayList<GrantedAuthority>();
		if (user.getRole() != null) {
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getRole().getRoleName());
			arrayAuths.add(auth);
		}
		return arrayAuths;
	}

	public String getFirstName() {
		return user.getPerson().getFirstName();
	}

	public String getLastName() {
		return user.getPerson().getLastName();
	}

	public String getEmail() {
		return user.getPerson().getEmail();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
}
