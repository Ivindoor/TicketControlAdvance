package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Box;

public interface BoxDao {
	/*
	 * 
	 */
	void saveBox(Box box);

	List<Box> findAllBoxes();

	void deleteBoxByIdBox(Long id_box);

	Box findBoxByIdBox(Long id_box);

	void updateBox(Box box);

	Box findBoxByBox(String box);//pendiente

	Box test();

	
	
	
}
