package com.sanmina.tk.orm.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Part_Family;
@Repository("part_FamilyDao")
@Transactional("txTicket")
public class Part_FamilyDaoImpl extends AbstractDAO implements Part_FamilyDao{
	/*
	 * 
	 */
	public void savePart_Family(Part_Family part_family) {
		persistTicket(part_family);
	}

	@SuppressWarnings("unchecked")
	public List<Part_Family> findAllPart_Family() {
		Criteria criteria = getSessionTicket().createCriteria(Part_Family.class);
		return (List<Part_Family>) criteria.list();
	}

	public void deletePart_FamilyById_family(Long id_family) {
		Query query = getSessionTicket().createSQLQuery("delete from Part_Family where id_family = :id_family");
		query.setLong("id_family", id_family);
		query.executeUpdate();
	}

	public Part_Family findPart_FamilytById_family(Long id_family) {
		System.out.println("??? "+id_family);
		Criteria criteria = getSessionTicket().createCriteria(Part_Family.class);
		criteria.add(Restrictions.eq("id_family", id_family));//
		return (Part_Family) criteria.uniqueResult();
	}

	public void updatePart_Family(Part_Family part_family) {
		getSessionTicket().update(part_family);
	}

	public Part_Family findPart_FamilyByPart_Family(String part_family) {
		Criteria criteria = getSessionTicket().createCriteria(Part_Family.class);
		criteria.add(Restrictions.eq("id_family", part_family));
		return (Part_Family) criteria.uniqueResult();
	}

	public Part_Family test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Part_Family WHERE id_family like '%1%'")
				.addEntity(Part_Family.class);
		return (Part_Family) query.uniqueResult();
	}

	
}
