package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_EchoStarValid_Config;

public interface tbl_EchoStarValid_ConfigDao {
	/*
	 * 
	 */
	void saveTbl_EchoStarValid_Config(tbl_EchoStarValid_Config tbl_echostarvalid_config);

	List<tbl_EchoStarValid_Config> findAllTbl_EchoStarValid_Config();

	void deleteTbl_EchoStarValid_ConfigByIdPartNumber(Long IdPartNumber);

	tbl_EchoStarValid_Config findTbl_EchoStarValid_ConfigByIdPartNumber(Long IdPartNumber);

	void updateTbl_EchoStarValid_Config(tbl_EchoStarValid_Config tbl_echostarvalid_config);

	tbl_EchoStarValid_Config findTbl_EchoStarValid_ConfigByTbl_EchoStarValid_Config(String tbl_echoStarValid_config);//pendiente

	tbl_EchoStarValid_Config test();

}
