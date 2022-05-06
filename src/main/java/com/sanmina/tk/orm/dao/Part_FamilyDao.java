package com.sanmina.tk.orm.dao;

import java.util.List;


import com.sanmina.tk.orm.model.Part_Family;

public interface Part_FamilyDao {
	/*
	 * 
	 */
	void savePart_Family(Part_Family part_family);

	List<Part_Family> findAllPart_Family();

	void deletePart_FamilyById_family(Long id_family);

	Part_Family findPart_FamilytById_family(Long id_family);

	void updatePart_Family(Part_Family part_family);

	Part_Family findPart_FamilyByPart_Family(String part_Family);//pendiente

	Part_Family test();

	
	
	
}
