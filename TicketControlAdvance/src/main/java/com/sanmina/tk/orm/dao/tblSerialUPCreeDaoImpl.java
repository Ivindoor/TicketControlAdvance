package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tblSerialUPCree;

@Repository("tblSerialUPCreeDao")
@Transactional("txsManagerTicket")
public class tblSerialUPCreeDaoImpl extends AbstractDAO implements tblSerialUPCreeDao{

	/*
	 * 
	 */
	public void savetblSerialUPCree(tblSerialUPCree tblserialupcree) {
		persistTicket(tblserialupcree);
	}

	@SuppressWarnings("unchecked")
	public List<tblSerialUPCree> findAllTblSerialUPCree() {
		Criteria criteria = getSessionTicket().createCriteria(tblSerialUPCree.class);
		return (List<tblSerialUPCree>) criteria.list();
	}

	public void deleteTblSerialUPCreeByIdSerialUPC(Long idSerialUPC) {
		Query query = getSessionTicket().createSQLQuery("delete from tblSerialUPCree where idSerialUPC = :idSerialUPC");
		query.setLong("idSerialUPC", idSerialUPC);
		query.executeUpdate();
	}

	public tblSerialUPCree findTblSerialUPCreeByIdSerialUPC(Long idSerialUPC) {
		System.out.println("??? "+idSerialUPC);
		Criteria criteria = getSessionTicket().createCriteria(tblSerialUPCree.class);
		criteria.add(Restrictions.eq("idSerialUPC", idSerialUPC));//
		return (tblSerialUPCree) criteria.uniqueResult();
	}

	public void updateTblSerialUPCree(tblSerialUPCree tblserialupcree) {
		getSessionTicket().update(tblserialupcree);
	}

	public tblSerialUPCree findTblSerialUPCreeByTblSerialUPCree(String tblserialupcree) {
		Criteria criteria = getSessionTicket().createCriteria(tblSerialUPCree.class);
		criteria.add(Restrictions.eq("idSerialUPC", tblserialupcree));
		return (tblSerialUPCree) criteria.uniqueResult();
	}

	public tblSerialUPCree test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tblSerialUPCree WHERE idSerialUPC like '%1%'")
				.addEntity(tblSerialUPCree.class);
		return (tblSerialUPCree) query.uniqueResult();
	}

	


}
