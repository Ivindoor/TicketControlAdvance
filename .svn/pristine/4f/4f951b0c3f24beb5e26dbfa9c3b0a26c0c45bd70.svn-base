package com.sanmina.tk.orm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.UserDao;
import com.sanmina.tk.orm.model.Users;


@Service("userService")
@Transactional
public class UserServiceImpl {
	@Autowired
	private UserDao dao;

	public void saveRdlUsers(Users users) {
		dao.saveUser(users);
	}

	public List<Users> findAllUsers() {
		return dao.findAllUsers();
	}

	public void deleteUsersByUserName(String usName) {
		dao.deleteUserByUserName(usName);
	}

	public void updateUsers(Users users) {
		dao.updateUser(users);
	}

	public Users findByUserName(String usName) {
		return dao.findUserByUserName(usName);
	}

	public Users findByUserLogin(String usLogin) {
		return dao.findUserByUserLogin(usLogin);
	}

	public Users test() {
		return dao.test();
	}
}
