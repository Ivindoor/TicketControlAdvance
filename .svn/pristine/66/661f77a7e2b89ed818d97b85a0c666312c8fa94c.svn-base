package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_ReprintTicket;

@Repository("tbl_ReprintTicketDao")
@Transactional("txTicket")
public class tbl_ReprintTicketDaoImpl extends AbstractDAO implements tbl_ReprintTicketDao{

	public void saveTbl_ReprintTicket(tbl_ReprintTicket tbl_reprintticket) {
		persistTicket(tbl_reprintticket);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_ReprintTicket> findAllTbl_ReprintTicket() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_ReprintTicket.class);
		return (List<tbl_ReprintTicket>) criteria.list();
	}

	public void deleteTbl_ReprintTicketByIdZpl(Long idZpl) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_ReprintTicket where idZpl = :idZpl");
		query.setLong("idZpl", idZpl);
		query.executeUpdate();
	}

	public tbl_ReprintTicket findTbl_ReprintTicketByIdZpl(Long idZpl) {
		System.out.println("??? "+idZpl);
		Criteria criteria = getSessionTicket().createCriteria(tbl_ReprintTicket.class);
		criteria.add(Restrictions.eq("idZpl", idZpl));//
		return (tbl_ReprintTicket) criteria.uniqueResult();
	}

	public void updateTbl_ReprintTicket(tbl_ReprintTicket tbl_reprintticket) {
		getSessionTicket().update(tbl_reprintticket);
	}

	public tbl_ReprintTicket findTbl_ReprintTicketByTbl_ReprintTicket(String tbl_reprintticket) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_ReprintTicket.class);
		criteria.add(Restrictions.eq("idZpl", tbl_reprintticket));
		return (tbl_ReprintTicket) criteria.uniqueResult();
	}

	public tbl_ReprintTicket test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_ReprintTicket WHERE idZpl like '%1%'")
				.addEntity(tbl_ReprintTicket.class);
		return (tbl_ReprintTicket) query.uniqueResult();
	}
	
}
