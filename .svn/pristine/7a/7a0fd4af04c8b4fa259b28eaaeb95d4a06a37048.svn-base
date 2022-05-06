package com.sanmina.tk.orm.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.sanmina.tk.orm.model.tbl_serialesCree;

@Repository("tbl_serialesCreeDao")
@Transactional("txsManagerTicket")
public class tbl_serialesCreeDaoImpl extends AbstractDAO implements tbl_serialesCreeDao {

	public void saveTbl_serialesCree(tbl_serialesCree tbl_serialescree) {
		persistTicket(tbl_serialescree);

	}

	// tbl_serialesCree
	@SuppressWarnings("unchecked")
	public List<tbl_serialesCree> findAllTbl_serialesCree() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_serialesCree.class);
		return (List<tbl_serialesCree>) criteria.list();
	}

	public void deleteTbl_serialesCreeByIdserial(Long idserial) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_serialesCree where idserial=:idserial");
		query.setLong("IdSerial", idserial);
		query.executeUpdate();
	}

	public tbl_serialesCree findTbl_serialesCreeByIdserial(Long idserial) {
		System.out.println(" ???" + idserial);
		Criteria criteria = getSessionTicket().createCriteria(tbl_serialesCree.class);
		criteria.add(Restrictions.eq("IdSerial", idserial));
		return (tbl_serialesCree) criteria.uniqueResult();
	}

	public void updateTbl_serialesCree(tbl_serialesCree tbl_serialescree) {
		getSessionTicket().update(tbl_serialescree);
	}

	public tbl_serialesCree findTbl_serialesCreeByTbl_serialesCree(String tbl_serialescree) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_serialesCree.class);
		criteria.add(Restrictions.eq("IdSerial", tbl_serialescree));
		return (tbl_serialesCree) criteria.uniqueResult();
	}

	
	
	
	public tbl_serialesCree test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_serialesCree WHERE idserial like '%1%'")
				.addEntity(tbl_serialesCree.class);
		return (tbl_serialesCree) query.uniqueResult();
	}

}
