package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_TicketDeleted;

@Repository("tbl_TicketDeletedDao")
@Transactional("txTicket")
public class tbl_TicketDeletedDaoImpl extends AbstractDAO implements tbl_TicketDeletedDao{

	public void saveTbl_TicketDeleted(tbl_TicketDeleted tbl_ticketdeleted) {
		persistTicket(tbl_ticketdeleted);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_TicketDeleted> findAllTbl_TicketDeleted() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_TicketDeleted.class);
		return (List<tbl_TicketDeleted>) criteria.list();
	}

	public void deleteTbl_TicketDeletedById_ticket(Long id_ticket) {
		getSessionTicket().delete(id_ticket);
	}

	public tbl_TicketDeleted findTbl_TicketDeletedById_ticket(Long id_ticket) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_TicketDeleted.class);
		criteria.add(Restrictions.eq("id_ticket", id_ticket));//
		return (tbl_TicketDeleted) criteria.uniqueResult();
	}

	public void updateTbl_TicketDeleted(tbl_TicketDeleted tbl_ticketdeleted) {
		getSessionTicket().update(tbl_ticketdeleted);
	}

	public tbl_TicketDeleted findTbl_TicketDeletedByTbl_TicketDeleted(String tbl_TicketDeleted) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_TicketDeleted.class);
		criteria.add(Restrictions.eq("id_ticket", tbl_TicketDeleted));
		return (tbl_TicketDeleted) criteria.uniqueResult();
	}

}
