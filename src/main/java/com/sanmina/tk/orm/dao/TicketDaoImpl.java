package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Ticket;

@Repository("ticketDao")
@Transactional("txTicket")
public class TicketDaoImpl extends AbstractDAO implements TicketDao {

	public void saveTicket(Ticket ticket) {
		persistTicket(ticket);
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findAllTickets() {
		Criteria criteria = getSessionTicket().createCriteria(Ticket.class);
		return (List<Ticket>) criteria.list();
	}

	public void deleteTicketByTicket(Ticket ticket) {
		getSessionTicket().delete(ticket);
	}

	public Ticket findTicketByIdTicket(Long idTicket) {
		Criteria criteria = getSessionTicket().createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("idTicket", idTicket));//
		return (Ticket) criteria.uniqueResult();
	}

	public void updateTicket(Ticket ticket) {
		getSessionTicket().update(ticket);
	}

	public Ticket findTicketByTicket(String ticket) {
		Criteria criteria = getSessionTicket().createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("ticket", ticket));
		return (Ticket) criteria.uniqueResult();
	}

	public Ticket findTicketByTicketInFloor(String ticket) {
		Criteria criteria = getSessionTicket().createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("ticket", ticket));
		criteria.add(Restrictions.eq("enabled", "1"));
		return (Ticket) criteria.uniqueResult();
	}
	
	public Ticket findTicketAvailable() {
		SQLQuery query = getSessionTicket().createSQLQuery("EXEC [SP_TicketDisponible]").addEntity(Ticket.class);
		return (Ticket) query.uniqueResult();
	}

	@Override
	public Ticket findTicketByContainer(String container) {
		Criteria criteria = getSessionTicket().createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("container", container));
		return (Ticket) criteria.uniqueResult();
	}

}
