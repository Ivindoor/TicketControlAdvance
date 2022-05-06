package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_SiemensConfig;

@Repository("tbl_SiemensConfigDao")
@Transactional("txTicket")
public class tbl_SiemensConfigDaoImpl extends AbstractDAO implements tbl_SiemensConfigDao{

	public void saveTbl_SiemensConfig(tbl_SiemensConfig tbl_siemensconfig) {
		persistTicket(tbl_siemensconfig);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_SiemensConfig> findAllTbl_SiemensConfig() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_SiemensConfig.class);
		return (List<tbl_SiemensConfig>) criteria.list();
	}

	public void deleteTbl_SiemensConfigByIdPartnumber(Long idpartnumber) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_SiemensConfig where idpartnumber = :idpartnumber");
		query.setLong("idpartnumber", idpartnumber);
		query.executeUpdate();
	}

	public tbl_SiemensConfig findTbl_SiemensConfigByIdpartnumber(Long idpartnumber) {
		System.out.println("??? "+idpartnumber);
		Criteria criteria = getSessionTicket().createCriteria(tbl_SiemensConfig.class);
		criteria.add(Restrictions.eq("idBox", idpartnumber));//
		return (tbl_SiemensConfig) criteria.uniqueResult();
	}

	public void updateTbl_SiemensConfig(tbl_SiemensConfig tbl_SiemensConfig) {
		getSessionTicket().update(tbl_SiemensConfig);
	}

	public tbl_SiemensConfig findTbl_SiemensConfigByTbl_SiemensConfig(String tbl_siemensconfig) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_SiemensConfig.class);
		criteria.add(Restrictions.eq("idpartnumber", tbl_siemensconfig));
		return (tbl_SiemensConfig) criteria.uniqueResult();
	}

	public tbl_SiemensConfig test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_SiemensConfig WHERE idpartnumber like '%1%'")
				.addEntity(tbl_SiemensConfig.class);
		return (tbl_SiemensConfig) query.uniqueResult();
	}

	@Override
	public void deleteTbl_SiemensConfigByIdpartnumber(Long idpartnumber) {
		
	}
	
}
