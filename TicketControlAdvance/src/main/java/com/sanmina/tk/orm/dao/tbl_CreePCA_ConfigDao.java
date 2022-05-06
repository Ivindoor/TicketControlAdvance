package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_CreePCA_Config;

public interface tbl_CreePCA_ConfigDao {

	/*
	 * 
	 */
	//tbl_CreePCA_Config
	void saveTbl_CreePCA_Config(tbl_CreePCA_Config tbl_Cree_Config);

	List<tbl_CreePCA_Config> findAllTbl_CreePCA_Config();

	void deleteTbl_CreePCA_ConfigByIdPartNumber(Long idPartNumber);

	tbl_CreePCA_Config findTbl_CreePCA_ConfigByIdPartNumber(Long idPartNumber);

	void updateTbl_CreePCA_Config(tbl_CreePCA_Config tbl_creepca_config);

	tbl_CreePCA_Config findTbl_CreePCA_ConfigByTbl_CreePCA_Config(String tbl_CreePCA_Config);//pendiente

	tbl_CreePCA_Config test();

}
