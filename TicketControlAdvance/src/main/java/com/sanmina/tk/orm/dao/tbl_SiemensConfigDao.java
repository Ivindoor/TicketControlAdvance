package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_SiemensConfig;

public interface tbl_SiemensConfigDao {
	/*
	 * 
	 */
	void saveTbl_SiemensConfig(tbl_SiemensConfig tbl_siemensconfig);

	List<tbl_SiemensConfig> findAllTbl_SiemensConfig();

	void deleteTbl_SiemensConfigByIdpartnumber(Long idpartnumber);

	tbl_SiemensConfig findTbl_SiemensConfigByIdpartnumber(Long idpartnumber);

	void updateTbl_SiemensConfig(tbl_SiemensConfig tbl_siemensconfig);

	tbl_SiemensConfig findTbl_SiemensConfigByTbl_SiemensConfig(String tbl_siemensconfig);//pendiente

	tbl_SiemensConfig test();

	
	
	
}
