package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tblSerialUPCree;

public interface tblSerialUPCreeDao {
	/*
	 * 
	 */
	void savetblSerialUPCree(tblSerialUPCree tblserialupcree);

	List<tblSerialUPCree> findAllTblSerialUPCree();

	void deleteTblSerialUPCreeByIdSerialUPC(Long idSerialUPC);

	tblSerialUPCree findTblSerialUPCreeByIdSerialUPC(Long idSerialUPC);

	void updateTblSerialUPCree(tblSerialUPCree tblserialupcree);

	tblSerialUPCree findTblSerialUPCreeByTblSerialUPCree(String tblserialupcree);//pendiente

	tblSerialUPCree test();

	
	
	
}
