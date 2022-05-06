package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_UserLogin;

public interface tbl_UserLoginDao {
	/*
	 * 
	 */
	void saveTbl_UserLogin(tbl_UserLogin tbl_userlogin);

	List<tbl_UserLogin> findAllTbl_UserLogin();

	void deleteTbl_UserLoginByIdUser(Long idUser);

	tbl_UserLogin findTbl_UserLoginByIdUser(Long idUser);

	void updateTbl_UserLogin(tbl_UserLogin tbl_userlogin);

	tbl_UserLogin findTbl_UserLoginByTbl_UserLogin(String tbl_UserLogin);//pendiente

	tbl_UserLogin test();

	
	
	
	
	
}
