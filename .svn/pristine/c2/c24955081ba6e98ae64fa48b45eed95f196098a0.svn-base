package com.sanmina.tk.orm.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Data_Base;

@Repository("data_BaseDao")
@Transactional("txTicket")
public class Data_BaseDaoImpl extends AbstractDAO implements Data_BaseDao{
	/*
	 * 
	 */
	public void saveData_Base(Data_Base data_base) {
		persistTicket(data_base);
	}

	@SuppressWarnings("unchecked")
	public List<Data_Base> findAllData_Bases() {
		Criteria criteria = getSessionTicket().createCriteria(Data_Base.class);
		return (List<Data_Base>) criteria.list();
	}

	public void deleteData_BaseByDb_id(Long db_id) {
		Query query = getSessionTicket().createSQLQuery("delete from Data_Base where db_id = :db_id");
		query.setLong("db_id", db_id);
		query.executeUpdate();
	}

	public Data_Base findData_basesByDb_id(Long db_id) {
		System.out.println("??? "+db_id);
		Criteria criteria = getSessionTicket().createCriteria(Data_Base.class);
		criteria.add(Restrictions.eq("db_id", db_id));//
		return (Data_Base) criteria.uniqueResult();
	}

	public void updateData_Base(Data_Base data_base) {
		getSessionTicket().update(data_base);
	}

	public Data_Base findData_BaseByData_Base(String data_base) {
		Criteria criteria = getSessionTicket().createCriteria(Data_Base.class);
		criteria.add(Restrictions.eq("db_id", data_base));
		return (Data_Base) criteria.uniqueResult();
	}

	public Data_Base test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Data_Base WHERE db_id like '%1%'")
				.addEntity(Data_Base.class);
		return (Data_Base) query.uniqueResult();
	}

	
}
