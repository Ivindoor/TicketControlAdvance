package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Ticket;

public interface TicketDao {
	
	void saveTicket(Ticket Ticket);

	List<Ticket> findAllTickets();

	void  deleteTicketByTicket(Ticket ticket);

	Ticket findTicketByIdTicket(Long idTicket);

	void updateTicket(Ticket ticket);

	Ticket findTicketByTicket(String ticket);
	
	Ticket findTicketByTicketInFloor(String ticket);

	Ticket findTicketByContainer(String container);

	Ticket findTicketAvailable();
	
}
