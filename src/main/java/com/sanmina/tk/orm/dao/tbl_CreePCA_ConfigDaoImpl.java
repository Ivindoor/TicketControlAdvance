package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_CreePCA_Config;

@Repository("tbl_CreePCA_ConfigDao")
@Transactional("txTicket")
public class tbl_CreePCA_ConfigDaoImpl extends AbstractDAO implements tbl_CreePCA_ConfigDao{

	public void saveTbl_CreePCA_Config(tbl_CreePCA_Config tbl_creepca_config) {
		persistTicket(tbl_creepca_config);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_CreePCA_Config> findAllTbl_CreePCA_Config() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_CreePCA_Config.class);
		return (List<tbl_CreePCA_Config>) criteria.list();
	}

	public void deleteTbl_CreePCA_ConfigByIdPartNumber(Long idPartNumber) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_CreePCA_Config where idPartNumber = :idPartNumber");
		query.setLong("idPartNumber", idPartNumber);
		query.executeUpdate();
	}

	public tbl_CreePCA_Config findTbl_CreePCA_ConfigByIdPartNumber(Long idPartNumber) {
		System.out.println("??? "+idPartNumber);
		Criteria criteria = getSessionTicket().createCriteria(tbl_CreePCA_Config.class);
		criteria.add(Restrictions.eq("idPartNumber", idPartNumber));//
		return (tbl_CreePCA_Config) criteria.uniqueResult();
	}

	public void updateTbl_CreePCA_Config(tbl_CreePCA_Config tbl_creepca_config) {
		getSessionTicket().update(tbl_creepca_config);
	}

	public tbl_CreePCA_Config findTbl_CreePCA_ConfigByTbl_CreePCA_Config(String tbl_creepca_config ) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_CreePCA_Config.class);
		criteria.add(Restrictions.eq("idPartNumber", tbl_creepca_config));
		return (tbl_CreePCA_Config) criteria.uniqueResult();
	}

	public tbl_CreePCA_Config test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_CreePCA_Config WHERE idPartNumber like '%1%'")
				.addEntity(tbl_CreePCA_Config.class);
		return (tbl_CreePCA_Config) query.uniqueResult();
	}
	
}
