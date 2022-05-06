package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tblStatusDelivery;

@Repository("tblStatusDeliveryDao")
@Transactional("txsManagerTicket")
public class tblStatusDeliveryDaoImpl extends AbstractDAO implements tblStatusDeliveryDao{
	/*
	 * 
	 */
	public void saveTblStatusDelivery(tblStatusDelivery tblstatusdelivery) {
		persistTicket(tblstatusdelivery);
	}

	@SuppressWarnings("unchecked")
	public List<tblStatusDelivery> findAllTblStatusDelivery() {
		Criteria criteria = getSessionTicket().createCriteria(tblStatusDelivery.class);
		return (List<tblStatusDelivery>) criteria.list();
	}

	public void deleteTblStatusDeliveryByIdStatus(Long idStatus) {
		Query query = getSessionTicket().createSQLQuery("delete from tblStatusDelivery where idStatus = :idStatus");
		query.setLong("idStatus", idStatus);
		query.executeUpdate();
	}

	public tblStatusDelivery findTblStatusDeliveryByIdStatus(Long idStatus) {
		System.out.println("??? "+idStatus);
		Criteria criteria = getSessionTicket().createCriteria(tblStatusDelivery.class);
		criteria.add(Restrictions.eq("idStatus", idStatus));//
		return (tblStatusDelivery) criteria.uniqueResult();
	}

	public void updateTblStatusDelivery(tblStatusDelivery tblstatusdelivery) {
		getSessionTicket().update(tblstatusdelivery);
	}

	public tblStatusDelivery findTblStatusDeliveryByTblStatusDelivery(String tblstatusdelivery) {
		Criteria criteria = getSessionTicket().createCriteria(tblStatusDelivery.class);
		criteria.add(Restrictions.eq("idStatus", tblstatusdelivery));
		return (tblStatusDelivery) criteria.uniqueResult();
	}

	public tblStatusDelivery test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tblStatusDelivery WHERE idStatus like '%1%'")
				.addEntity(tblStatusDelivery.class);
		return (tblStatusDelivery) query.uniqueResult();
	}

	
}
