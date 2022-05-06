package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Cree_config;


@Repository("cree_configDao")
@Transactional("txTicket")
public class Cree_configDaoImpl extends AbstractDAO implements Cree_configDao{
	/*
	 * 
	 */
	public void saveCree_config(Cree_config cree_config) {
		persistTicket(cree_config);
	}

	@SuppressWarnings("unchecked")
	public List<Cree_config> findAllCree_config() {
		Criteria criteria = getSessionTicket().createCriteria(Cree_config.class);
		return (List<Cree_config>) criteria.list();
	}

	public void deleteCree_configByIdConfig(Long idConfig) {
		Query query = getSessionTicket().createSQLQuery("delete from Cree_config where idConfig = :idConfig");
		query.setLong("idConfig", idConfig);
		query.executeUpdate();
	}

	public Cree_config findCree_configByIdConfig(Long idConfig) {
		System.out.println("??? "+idConfig);
		Criteria criteria = getSessionTicket().createCriteria(Cree_config.class);
		criteria.add(Restrictions.eq("idConfig", idConfig));//
		return (Cree_config) criteria.uniqueResult();
	}

	public void updateCree_config(Cree_config cree_config) {
		getSessionTicket().update(cree_config);
	}

	public Cree_config findCree_configByCree_config(String cree_config) {
		Criteria criteria = getSessionTicket().createCriteria(Cree_config.class);
		criteria.add(Restrictions.eq("idConfig", cree_config));
		return (Cree_config) criteria.uniqueResult();
	}

	public Cree_config test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Cree_config WHERE idConfig like '%1%'")
				.addEntity(Cree_config.class);
		return (Cree_config) query.uniqueResult();
	}

	
	

	
	
}
