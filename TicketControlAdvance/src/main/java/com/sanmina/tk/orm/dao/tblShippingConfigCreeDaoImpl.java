package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tblShippingConfigCree;

@Repository("tblShippingConfigCreeDao")
@Transactional("txsManagerTicket")
public class tblShippingConfigCreeDaoImpl extends AbstractDAO implements tblShippingConfigCreeDao{
	/*
	 * 
	 */
	public void saveTblShippingConfigCree(tblShippingConfigCree tblshippingconfigcree) {
		persistTicket(tblshippingconfigcree);
	}

	@SuppressWarnings("unchecked")
	public List<tblShippingConfigCree> findAllTblShippingConfigCree() {
		Criteria criteria = getSessionTicket().createCriteria(tblShippingConfigCree.class);
		return (List<tblShippingConfigCree>) criteria.list();
	}

	public void deleteTblShippingConfigCreeByIdPartNumber(Long idPartNumber) {
		Query query = getSessionTicket().createSQLQuery("delete from tblShippingConfigCree where idPartNumber = :idPartNumber");
		query.setLong("idPartNumber", idPartNumber);
		query.executeUpdate();
	}

	public tblShippingConfigCree findTblShippingConfigCreeByIdPartNumber(Long idPartNumber) {
		System.out.println("??? "+idPartNumber);
		Criteria criteria = getSessionTicket().createCriteria(tblShippingConfigCree.class);
		criteria.add(Restrictions.eq("idPartNumber", idPartNumber));//
		return (tblShippingConfigCree) criteria.uniqueResult();
	}

	
	public void updateTblShippingConfigCree(tblShippingConfigCree tblShippingconfigcree) {
		getSessionTicket().update(tblShippingconfigcree);
	}

	public tblShippingConfigCree findTblShippingConfigCreeByTblShippingConfigCree(String tblshippingconfigcree) {
		Criteria criteria = getSessionTicket().createCriteria(tblShippingConfigCree.class);
		criteria.add(Restrictions.eq("idPartNumber", tblshippingconfigcree));
		return (tblShippingConfigCree) criteria.uniqueResult();
	}

	public tblShippingConfigCree  test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tblShippingConfigCree WHERE idPartNumber like '%1%'")
				.addEntity(tblShippingConfigCree.class);
		return (tblShippingConfigCree) query.uniqueResult();
	}

	
}
