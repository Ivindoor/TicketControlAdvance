package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_MDS;

public interface tbl_MDSDao {
	/*
	 * 
	 */
	void saveTbl_MDS( tbl_MDS  tbl_mds);

	List< tbl_MDS> findAllTbl_MDS();

	void deleteTbl_MDSByIdMDS(Long idMDS);

	tbl_MDS  findTbl_MDSByIdMDS(Long idMDS);

	void updateTbl_MDS(tbl_MDS tbl_mds);

	tbl_MDS findTbl_MDSByTbl_MDS(String Tbl_MDS);//pendiente

	tbl_MDS test();

}
