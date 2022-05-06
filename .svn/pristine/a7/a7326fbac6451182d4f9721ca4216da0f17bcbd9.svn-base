package com.sanmina.tk.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sanmina.tk.orm.dao.UserDao;
import com.sanmina.tk.orm.model.Users;

import com.sanmina.tk.config.LoginInActiveDirectory;

@Service("UserCredentialService")
public class UserCredentialService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	// @Getter @Setter private String usPassword;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("hello ");
		Users userLocal;
		LoginInActiveDirectory liad = new LoginInActiveDirectory();
		userLocal = userDao.findUserByUserLogin(username);
		if (userLocal != null) {
			System.out.println("hello 2");
			if (username.contains("_")) {
				// is active directory user
				if (!liad.login(username, userLocal.getPassword())) {
					GrantedAuthority authority = new SimpleGrantedAuthority("DBO");
					UserDetails userD = (UserDetails) new User(userLocal.getUniversalLogin(), userLocal.getPassword(),
							Arrays.asList(authority));
					return userD;
				}
			}
		}
		else{
			System.out.println("User nulo");
		}
		throw new UsernameNotFoundException("User don't have permissions " + username);
	}
}
