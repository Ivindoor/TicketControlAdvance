package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_Emerson_Config;
@Repository("tbl_Emerson_ConfigDao")
@Transactional("txsManagerTicket")
public class tbl_Emerson_ConfigDaoImpl extends AbstractDAO implements tbl_Emerson_ConfigDao{
	/*
	 * 
	 */
	public void saveTbl_Emerson_Config(tbl_Emerson_Config tbl_emerson_config) {
		persistTicket(tbl_emerson_config);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_Emerson_Config> findAllTbl_Emerson_Config() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_Emerson_Config.class);
		return (List<tbl_Emerson_Config>) criteria.list();
	}

	public void deleteTbl_Emerson_ConfigByIdPart_Number(Long idPart_Number) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_Emerson_Config where idPart_Number = :idPart_Number");
		query.setLong("idPart_Number", idPart_Number);
		query.executeUpdate();
	}

	public tbl_Emerson_Config findTbl_Emerson_ConfigByIdPart_Number(Long idPart_Number) {
		System.out.println("??? "+idPart_Number);
		Criteria criteria = getSessionTicket().createCriteria(tbl_Emerson_Config.class);
		criteria.add(Restrictions.eq("idPart_Number", idPart_Number));//
		return (tbl_Emerson_Config) criteria.uniqueResult();
	}

	public void updateTbl_Emerson_Config(tbl_Emerson_Config tbl_emerson_config) {
		getSessionTicket().update(tbl_emerson_config);
	}

	public tbl_Emerson_Config findTbl_Emerson_ConfigByTbl_Emerson_Config(String tbl_emerson_config) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_Emerson_Config.class);
		criteria.add(Restrictions.eq("idBox", tbl_emerson_config));
		return (tbl_Emerson_Config) criteria.uniqueResult();
	}

	public tbl_Emerson_Config test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_Emerson_Config WHERE idPart_Number like '%1%'")
				.addEntity(tbl_Emerson_Config.class);
		return (tbl_Emerson_Config) query.uniqueResult();
	}

	


}
