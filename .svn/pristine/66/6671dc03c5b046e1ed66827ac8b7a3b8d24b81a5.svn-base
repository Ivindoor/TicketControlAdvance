package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Box;

@Repository("boxDao")
@Transactional("txsManagerTicket")
public class BoxDaoImpl extends AbstractDAO implements BoxDao{
	/*
	 * 
	 */
	public void saveBox(Box box) {
		persistTicket(box);
	}

	@SuppressWarnings("unchecked")
	public List<Box> findAllBoxes() {
		Criteria criteria = getSessionTicket().createCriteria(Box.class);
		return (List<Box>) criteria.list();
	}

	public void deleteBoxByIdBox(Long id_box) {
		Query query = getSessionTicket().createSQLQuery("delete from Box where idBox = :idBox");
		query.setLong("idBox", id_box);
		query.executeUpdate();
	}

	public Box findBoxByIdBox(Long id_box) {
		System.out.println("??? "+id_box);
		Criteria criteria = getSessionTicket().createCriteria(Box.class);
		criteria.add(Restrictions.eq("idBox", id_box));//
		return (Box) criteria.uniqueResult();
	}

	public void updateBox(Box box) {
		getSessionTicket().update(box);
	}

	public Box findBoxByBox(String box) {
		Criteria criteria = getSessionTicket().createCriteria(Box.class);
		criteria.add(Restrictions.eq("idBox", box));
		return (Box) criteria.uniqueResult();
	}

	public Box test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Box WHERE id_box like '%1%'")
				.addEntity(Box.class);
		return (Box) query.uniqueResult();
	}

	


	
}
