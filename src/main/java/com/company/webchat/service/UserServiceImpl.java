package com.company.webchat.service;

import com.company.webchat.entity.User;
import com.company.webchat.dao.UserDao;
import com.company.webchat.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		System.out.println(user.getPassword());
		// give user default role of "employee"
//		user.setRoles(Arrays.asList(roleDao.findRoleByName("EMPLOYEE")));

		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		System.out.println();
		return org.springframework.security.core.userdetails.User
				.builder()
				.username(user.getUserName())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}


}
