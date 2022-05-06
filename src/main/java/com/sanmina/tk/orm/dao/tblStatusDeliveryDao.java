package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tblStatusDelivery;

public interface tblStatusDeliveryDao {
	/*
	 * 
	 */
	void saveTblStatusDelivery(tblStatusDelivery tblstatusdelivery);

	List<tblStatusDelivery> findAllTblStatusDelivery();

	void deleteTblStatusDeliveryByIdStatus(Long idStatus);

	tblStatusDelivery findTblStatusDeliveryByIdStatus(Long idStatus);

	void updateTblStatusDelivery(tblStatusDelivery tblstatusdelivery);

	tblStatusDelivery findTblStatusDeliveryByTblStatusDelivery(String tblstatusdelivery);//pendiente

	tblStatusDelivery test();

	
	
	
}
