package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Part_Number;
@Repository("part_NumberDao")
@Transactional("txTicket")
public class Part_NumberDaoImpl extends AbstractDAO implements Part_NumberDao{
	/*
	 * 
	 */
	public void savePart_Number(Part_Number part_number) {
		persistTicket(part_number);
	}

	@SuppressWarnings("unchecked")
	public List<Part_Number> findAllPart_Numbers() {
		Criteria criteria = getSessionTicket().createCriteria(Part_Number.class);
		return (List<Part_Number>) criteria.list();
	}

	public void deletePart_NumberById_partnumber(Long id_partnumber) {
		Query query = getSessionTicket().createSQLQuery("delete from Part_Number where id_partnumber = :id_partnumber");
		query.setLong("id_partnumber", id_partnumber);
		query.executeUpdate();
	}

	public Part_Number findPart_NumberById_partnumber(Long id_partnumber) {
		System.out.println("??? "+id_partnumber);
		Criteria criteria = getSessionTicket().createCriteria(Part_Number.class);
		criteria.add(Restrictions.eq("id_partnumber", id_partnumber));//
		return (Part_Number) criteria.uniqueResult();
	}

	public void updatePart_Number(Part_Number part_number) {
		getSessionTicket().update(part_number);
	}

	public Part_Number findPart_NumberByPart_Number(String part_number) {
		Criteria criteria = getSessionTicket().createCriteria(Part_Number.class);
		criteria.add(Restrictions.eq("id_partnumber", part_number));
		return (Part_Number) criteria.uniqueResult();
	}

	public Part_Number test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Part_Number WHERE id_partnumber like '%1%'")
				.addEntity(Part_Number.class);
		return (Part_Number) query.uniqueResult();
	}

	


}
