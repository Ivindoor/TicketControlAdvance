package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tbl_ReprintTicket;

public interface tbl_ReprintTicketDao {
	/*
	 * 
	 */
	void saveTbl_ReprintTicket( tbl_ReprintTicket  tbl_reprintticket);

	List< tbl_ReprintTicket> findAllTbl_ReprintTicket();

	void deleteTbl_ReprintTicketByIdZpl(Long idZpl);

	 tbl_ReprintTicket findTbl_ReprintTicketByIdZpl(Long idZpl);

	void updateTbl_ReprintTicket(tbl_ReprintTicket  tbl_reprintticket);

	 tbl_ReprintTicket findTbl_ReprintTicketByTbl_ReprintTicket(String tbl_reprintticket);//pendiente

	 tbl_ReprintTicket test();
   
	
	
	
	
	
	
	
}
