package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_Emerson_Config;

public interface tbl_Emerson_ConfigDao {

	/*
	 * 
	 */
	void saveTbl_Emerson_Config(tbl_Emerson_Config tbl_emerson_config);

	List<tbl_Emerson_Config> findAllTbl_Emerson_Config();

	void deleteTbl_Emerson_ConfigByIdPart_Number(Long idPart_Number);

	tbl_Emerson_Config findTbl_Emerson_ConfigByIdPart_Number(Long idPart_Number);

	void updateTbl_Emerson_Config(tbl_Emerson_Config tbl_emerson_config);

	tbl_Emerson_Config findTbl_Emerson_ConfigByTbl_Emerson_Config(String tbl_emerson_config);//pendiente

	tbl_Emerson_Config test();

}
