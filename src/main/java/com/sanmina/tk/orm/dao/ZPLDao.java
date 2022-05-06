package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.ZPL;

public interface ZPLDao {
	/*
	 * 
	 */
	void saveZPL(ZPL zpl);

	List<ZPL> findAllZPL();

	void deleteZPLById(Long id);

	ZPL findZPLById(Long id);
	
	ZPL findZPLByIdProject(Long idProject);

	void updateZPL(ZPL zpl);

	ZPL findZPLByZPL(String zpl);//pendiente

	ZPL test();

	
	
	
	
}
