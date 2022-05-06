package com.sanmina.tk.orm.dao;

import java.util.List;
import com.sanmina.tk.orm.model.tbl_DeliveriesDeleted;

public interface tbl_DeliveriesDeletedDao {
	/*
	 * 
	 */
	void saveTbl_DeliveriesDeleted(tbl_DeliveriesDeleted tbl_deliveriesdeleted);

	List<tbl_DeliveriesDeleted> findAllTbl_DeliveriesDeleted();

	void deleteTbl_DeliveriesDeletedByDelivery(Long delivery);

	tbl_DeliveriesDeleted findTbl_DeliveriesDeletedByDelivery(Long Delivery);

	void updateTbl_DeliveriesDeleted(tbl_DeliveriesDeleted tbl_deliveriesdeleted);

	tbl_DeliveriesDeleted findTbl_DeliveriesDeletedBytbl_DeliveriesDeleted(String tbl_deliveriesdeleted);//pendiente

	tbl_DeliveriesDeleted test();

	
	
}
