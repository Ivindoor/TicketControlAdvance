package com.sanmina.tk.orm.dao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Coment;

@Repository("comentDao")
@Transactional("txsManagerTicket")
public class ComentDaoImpl extends AbstractDAO implements ComentDao{
	

	public void saveComent(Coment coment){
		
		persistTicket(coment);
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Coment> findAllComents() {
		Criteria criteria = getSessionTicket().createCriteria(Coment.class);
		return (List<Coment>) criteria.list();}
	
	
	public void deleteComentByIdComent(Long id_coment) {
		Query query = getSessionTicket().createSQLQuery("delete from Coment where id_coment = :id_coment");
		query.setLong("id_coment", id_coment);
		query.executeUpdate();
	}

	
	
	
	public Coment findComentByIdComent(Long id_coment) {
		System.out.println("??? "+id_coment);
		Criteria criteria = getSessionTicket().createCriteria(Coment.class);
		criteria.add(Restrictions.eq("id_coment", id_coment));
		return (Coment) criteria.uniqueResult();
	}

	
	
	public void updateComent(Coment coment) {
		getSessionTicket().update(coment);
	}

	
	
	public Coment findComentByComent(String coment) {
		Criteria criteria = getSessionTicket().createCriteria(Coment.class);
		criteria.add(Restrictions.eq("id_coment", coment));
		return (Coment) criteria.uniqueResult();
	}

	
	
	public Coment test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Coment WHERE id_coment like '%ivan%'")
				.addEntity(Coment.class);
		return (Coment) query.uniqueResult();
	}

	
	
	
}

