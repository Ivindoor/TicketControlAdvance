package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_serialesCree;

public interface tbl_serialesCreeDao {
	/*
	 * 
	 */
	void saveTbl_serialesCree(tbl_serialesCree tbl_serialescree);

	List<tbl_serialesCree> findAllTbl_serialesCree();

	void deleteTbl_serialesCreeByIdserial(Long Idserial);

	tbl_serialesCree findTbl_serialesCreeByIdserial(Long Idserial);

	void updateTbl_serialesCree(tbl_serialesCree tbl_serialescree);

	tbl_serialesCree findTbl_serialesCreeByTbl_serialesCree(String tbl_serialescree);//pendiente

	tbl_serialesCree test();

}
