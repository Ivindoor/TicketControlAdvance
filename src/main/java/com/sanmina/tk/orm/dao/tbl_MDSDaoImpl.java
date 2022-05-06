package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_MDS;

@Repository("tbl_MDSDao")
@Transactional("txTicket")
public class tbl_MDSDaoImpl  extends AbstractDAO implements tbl_MDSDao{
	
	public void saveTbl_MDS(tbl_MDS tbl_mds) {
		persistTicket(tbl_mds);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_MDS> findAllTbl_MDS() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_MDS.class);
		return (List<tbl_MDS>) criteria.list();
	}

	public void deleteTbl_MDSByIdMDS(Long idMDS) {
		Query query = getSessionTicket().createSQLQuery("delete from Box where idMDS = :idMDS");
		query.setLong("idMDS", idMDS);
		query.executeUpdate();
	}

	public tbl_MDS findTbl_MDSByIdMDS(Long idMDS) {
		System.out.println("??? "+idMDS);
		Criteria criteria = getSessionTicket().createCriteria(tbl_MDS.class);
		criteria.add(Restrictions.eq("idMDS", idMDS));//
		return (tbl_MDS) criteria.uniqueResult();
	}

	public void updateTbl_MDS(tbl_MDS tbl_mds) {
		getSessionTicket().update(tbl_mds);
	}

	public tbl_MDS findTbl_MDSByTbl_MDS(String tbl_mds) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_MDS.class);
		criteria.add(Restrictions.eq("idMDS", tbl_mds));
		return (tbl_MDS) criteria.uniqueResult();
	}

	public tbl_MDS test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_MDS WHERE idMDS like '%1%'")
				.addEntity(tbl_MDS.class);
		return (tbl_MDS) query.uniqueResult();
	}
	
}
