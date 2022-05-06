package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Serie_Ticket;
@Repository("serie_TicketDao")
@Transactional("txTicket")
public class Serie_TicketDaoImpl extends AbstractDAO implements Serie_TicketDao  {

	public void saveSerie_Ticket(Serie_Ticket serie_ticket) {
		persistTicket(serie_ticket);
	}

	@SuppressWarnings("unchecked")
	public List<Serie_Ticket> findAllSerie_Tickets() {
		Criteria criteria = getSessionTicket().createCriteria(Serie_Ticket.class);
		return (List<Serie_Ticket>) criteria.list();
	}

	public void deleteSerie_TciketsById_serie(Long id_serie) {
		Query query = getSessionTicket().createSQLQuery("delete from Serie_Ticket where id_serie = :id_serie");
		query.setLong("id_serie", id_serie);
		query.executeUpdate();
	}

	public Serie_Ticket findSerie_TicketById_serie(Long id_serie) {
		System.out.println("??? "+id_serie);
		Criteria criteria = getSessionTicket().createCriteria(Serie_Ticket.class);
		criteria.add(Restrictions.eq("id_serie", id_serie));//
		return (Serie_Ticket) criteria.uniqueResult();
	}

	public void updateSerie_Ticket(Serie_Ticket serie_ticket) {
		getSessionTicket().update(serie_ticket);
	}

	public Serie_Ticket findSerie_TicketBySerie_Ticket(String serie_ticket) {
		Criteria criteria = getSessionTicket().createCriteria(Serie_Ticket.class);
		criteria.add(Restrictions.eq("id_serie", serie_ticket));
		return (Serie_Ticket) criteria.uniqueResult();
	}

	public Serie_Ticket test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Serie_Ticket WHERE id_serie like '%1%'")
				.addEntity(Serie_Ticket.class);
		return (Serie_Ticket) query.uniqueResult();
	}


	
}
