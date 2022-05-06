package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tblShippingConfigCree;

public interface tblShippingConfigCreeDao {
	/*
	 * 
	 */
	void saveTblShippingConfigCree(tblShippingConfigCree tblshippingconfigcree);

	List<tblShippingConfigCree> findAllTblShippingConfigCree();

	void deleteTblShippingConfigCreeByIdPartNumber(Long idPartNumber);

	tblShippingConfigCree findTblShippingConfigCreeByIdPartNumber(Long idPartNumber);

	void updateTblShippingConfigCree(tblShippingConfigCree tblshippingconfigcree);

	tblShippingConfigCree findTblShippingConfigCreeByTblShippingConfigCree(String tblShippingConfigCree);//pendiente

	tblShippingConfigCree test();

	
	
	
}
