package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.MappedPartNumber;

public interface MappedPartNumberDao {

	public void saveMapped(MappedPartNumber mapped);
	
	public void daleteMapped(MappedPartNumber mapped);
	
	public void updateMapped(MappedPartNumber mapped);
	
	public List<MappedPartNumber> findAllMapped();
	
	public MappedPartNumber findMappedByPartSanmina(String partSanmina);
	
	public MappedPartNumber findMappedByPartSanminaActive(String partSanmina);
	
}
