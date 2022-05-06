package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_DeliveriesDeleted;

@Repository("tbl_DeliveriesDeletedDao")
@Transactional("txTicket")
public class tbl_DeliveriesDeletedDaoImpl extends AbstractDAO implements tbl_DeliveriesDeletedDao{

	public void saveTbl_DeliveriesDeleted(tbl_DeliveriesDeleted tbl_deliveriesdeleted) {
		persistTicket(tbl_deliveriesdeleted);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_DeliveriesDeleted> findAllTbl_DeliveriesDeleted() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_DeliveriesDeleted.class);
		return (List<tbl_DeliveriesDeleted>) criteria.list();
	}

	public void deleteTbl_DeliveriesDeletedByDelivery(Long Delivery) {
		Query query = getSessionTicket().createSQLQuery("delete from Box where idBox = :idBox");
		query.setLong("Delivery", Delivery);
		query.executeUpdate();
	}

	public tbl_DeliveriesDeleted findTbl_DeliveriesDeletedByDelivery(Long Delivery) {
		System.out.println("??? "+Delivery);
		Criteria criteria = getSessionTicket().createCriteria(tbl_DeliveriesDeleted.class);
		criteria.add(Restrictions.eq("Delivery", Delivery));//
		return (tbl_DeliveriesDeleted) criteria.uniqueResult();
	}

	public void updateTbl_DeliveriesDeleted(tbl_DeliveriesDeleted tbl_deliveriesdeleted) {
		getSessionTicket().update(tbl_deliveriesdeleted);
	}

	public tbl_DeliveriesDeleted findTbl_DeliveriesDeletedBytbl_DeliveriesDeleted(String tbl_deliveriesdeleted) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_DeliveriesDeleted.class);
		criteria.add(Restrictions.eq("Delivery", tbl_deliveriesdeleted));
		return (tbl_DeliveriesDeleted) criteria.uniqueResult();
	}

	public tbl_DeliveriesDeleted test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_DeliveriesDeleted WHERE Delivery like '%1%'")
				.addEntity(tbl_DeliveriesDeleted.class);
		return (tbl_DeliveriesDeleted) query.uniqueResult();
	}

}
