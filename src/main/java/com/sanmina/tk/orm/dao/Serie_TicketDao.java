package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Serie_Ticket;

public interface Serie_TicketDao {

	
	/*
	 * 
	 */
	void saveSerie_Ticket(Serie_Ticket serie_ticket);

	List<Serie_Ticket> findAllSerie_Tickets();

	void deleteSerie_TciketsById_serie(Long id_serie);

	Serie_Ticket findSerie_TicketById_serie(Long id_serie);

	void updateSerie_Ticket(Serie_Ticket serie_ticket);

	Serie_Ticket findSerie_TicketBySerie_Ticket(String serie_ticket);//pendiente

	Serie_Ticket test();

	
	
	
}
