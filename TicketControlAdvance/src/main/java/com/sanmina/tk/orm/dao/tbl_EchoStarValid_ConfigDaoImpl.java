package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_EchoStarValid_Config;
@Repository("tbl_EchoStarValid_ConfigDao")
@Transactional("txsManagerTicket")
public class tbl_EchoStarValid_ConfigDaoImpl extends AbstractDAO implements tbl_EchoStarValid_ConfigDao{
	/*
	 * 
	 */
	public void saveTbl_EchoStarValid_Config(tbl_EchoStarValid_Config tbl_echostarvalid_config) {
		persistTicket(tbl_echostarvalid_config);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_EchoStarValid_Config> findAllTbl_EchoStarValid_Config() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_EchoStarValid_Config.class);
		return (List<tbl_EchoStarValid_Config>) criteria.list();
	}

	public void deleteTbl_EchoStarValid_ConfigByIdPartNumber(Long IdPartNumber) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_EchoStarValid_Config where IdPartNumber = :IdPartNumber");
		query.setLong("IdPartNumber", IdPartNumber);
		query.executeUpdate();
	}

	public tbl_EchoStarValid_Config findTbl_EchoStarValid_ConfigByIdPartNumber(Long IdPartNumber) {
		System.out.println("??? "+IdPartNumber);
		Criteria criteria = getSessionTicket().createCriteria(tbl_EchoStarValid_Config.class);
		criteria.add(Restrictions.eq("IdPartNumber", IdPartNumber));//
		return (tbl_EchoStarValid_Config) criteria.uniqueResult();
	}

	public void updateTbl_EchoStarValid_Config(tbl_EchoStarValid_Config tbl_echostarvalid_config) {
		getSessionTicket().update(tbl_echostarvalid_config);
	}

	public tbl_EchoStarValid_Config findTbl_EchoStarValid_ConfigByTbl_EchoStarValid_Config(String tbl_echostarvalid_config) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_EchoStarValid_Config.class);
		criteria.add(Restrictions.eq("IdPartNumber", tbl_echostarvalid_config));
		return (tbl_EchoStarValid_Config) criteria.uniqueResult();
	}

	public tbl_EchoStarValid_Config test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_EchoStarValid_Config WHERE IdPartNumber like '%1%'")
				.addEntity(tbl_EchoStarValid_Config.class);
		return (tbl_EchoStarValid_Config) query.uniqueResult();
	}

	
	

	



}
