package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_TypeUser;

public interface tbl_TypeUserDao {
	/*
	 * 
	 */
	void saveTbl_TypeUser(tbl_TypeUser tbl_typeuser);

	List<tbl_TypeUser> findAllTbl_TypeUser();

	void deleteTbl_TypeUserByIdTypeUser(Long idTypeUser );

	tbl_TypeUser findTbl_TypeUserByIdTypeUser(Long idTypeUser);

	void updateTbl_TypeUser(tbl_TypeUser tbl_typeuser);

	tbl_TypeUser findTbl_TypeUserByTbl_TypeUser(String tbl_TypeUser);//pendiente

	tbl_TypeUser test();

	
	
	
}
