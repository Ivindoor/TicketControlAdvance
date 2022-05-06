package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tblCheckedDelivery;

@Repository("tblCheckedDeliveryDao")
@Transactional("txTicket")
public class tblCheckedDeliveryDaoImpl extends AbstractDAO implements  tblCheckedDeliveryDao{

	public void saveTblCheckedDelivery(tblCheckedDelivery tblcheckeddelivery) {
		persistTicket(tblcheckeddelivery);
	}

	@SuppressWarnings("unchecked")
	public List<tblCheckedDelivery> findAllTblCheckedDelivery() {
		Criteria criteria = getSessionTicket().createCriteria(tblCheckedDelivery.class);
		return (List<tblCheckedDelivery>) criteria.list();
	}

	public void deleteTblCheckedDeliveryByIdDelivery(Long idDelivery) {
		Query query = getSessionTicket().createSQLQuery("delete from tblCheckedDelivery where idDelivery = :idDelivery");
		query.setLong("idDelivery", idDelivery);
		query.executeUpdate();
	}

	public tblCheckedDelivery findTblCheckedDeliveryByIdDelivery(Long idDelivery) {
		System.out.println("??? "+idDelivery);
		Criteria criteria = getSessionTicket().createCriteria(tblCheckedDelivery.class);
		criteria.add(Restrictions.eq("idDelivery", idDelivery));//
		return (tblCheckedDelivery) criteria.uniqueResult();
	}

	public void updateTblCheckedDelivery(tblCheckedDelivery tblcheckeddelivery) {
		getSessionTicket().update(tblcheckeddelivery);
	}

	public tblCheckedDelivery findTblCheckedDeliveryByTblCheckedDelivery(String tblcheckeddelivery) {
		Criteria criteria = getSessionTicket().createCriteria(tblCheckedDelivery.class);
		criteria.add(Restrictions.eq("idDelivery", tblcheckeddelivery));
		return (tblCheckedDelivery) criteria.uniqueResult();
	}

	public tblCheckedDelivery test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tblCheckedDelivery WHERE idDelivery like '%1%'")
				.addEntity(tblCheckedDelivery.class);
		return (tblCheckedDelivery) query.uniqueResult();
	}
	
}
