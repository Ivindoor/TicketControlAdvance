package com.sanmina.tk.orm.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.ZPL;

@Repository("zPLDao")
@Transactional("txsManagerTicket")
public class ZPLDaoImpl  extends AbstractDAO implements ZPLDao{

	public void saveZPL(ZPL zpl) {
		persistTicket(zpl);
	}

	@SuppressWarnings("unchecked")
	public List<ZPL> findAllZPL() {
		Criteria criteria = getSessionTicket().createCriteria(ZPL.class);

		return (List<ZPL>) criteria.list();
	}

	public void deleteZPLById(Long id) {
		Query query = getSessionTicket().createSQLQuery("delete from zpl where id_zpl =: id_zpl ");
		query.setLong("id", id);
		query.executeUpdate();
	}

	public ZPL findZPLById(Long id) {
		System.out.println("???" + id);
		Criteria criteria = getSessionTicket().createCriteria(ZPL.class);
		criteria.add(Restrictions.eq("id", id));
		return (ZPL) criteria.uniqueResult();
	}
	
	public ZPL findZPLByIdProject(Long idProject) {
		System.out.println("***************************");
		Criteria criteria = getSessionTicket().createCriteria(ZPL.class);
		criteria.add(Restrictions.eq("idProject", idProject));
		return (ZPL) criteria.uniqueResult();
	}

	public void updateZPL(ZPL zpl) {
		getSessionTicket().update(zpl);
	}

	public ZPL findZPLByZPL(String zpl) {
		Criteria criteria = getSessionTicket().createCriteria(ZPL.class);
		criteria.add(Restrictions.eqOrIsNull("id", zpl));
		return (ZPL) criteria.uniqueResult();
	}

	public ZPL test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM zpl WHERE id_zpl like '%1%'").addEntity(ZPL.class);
		return (ZPL) query.uniqueResult();
	}

}
