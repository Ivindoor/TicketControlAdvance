package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.MappedPartNumber;

@Transactional("txTicket")
@Service("MappedPartNumberDao")
public class MappedPartNumberDaoImpl extends AbstractDAO implements MappedPartNumberDao{

	@Override
	public void saveMapped(MappedPartNumber mapped) {
		// TODO Auto-generated method stub
		getSessionTicket().persist(mapped);
	}

	@Override
	public void daleteMapped(MappedPartNumber mapped) {
		// TODO Auto-generated method stub
		getSessionTicket().delete(mapped);
	}

	@Override
	public void updateMapped(MappedPartNumber mapped) {
		// TODO Auto-generated method stub
		getSessionTicket().update(mapped);
	}
	
	@SuppressWarnings("unchecked")
	public List<MappedPartNumber> findAllMapped() {
		Criteria criteria = getSessionTicket().createCriteria(MappedPartNumber.class);
		return (List<MappedPartNumber>) criteria.list();
	}
	
	@Override
	public MappedPartNumber findMappedByPartSanmina(String partSanmina) {
		// TODO 
		Criteria criteria = getSessionTicket().createCriteria(MappedPartNumber.class);
		criteria.add(Restrictions.eq("partNumberSanmina", partSanmina));
		return (MappedPartNumber) criteria.uniqueResult();
	}

	@Override
	public MappedPartNumber findMappedByPartSanminaActive(String partSanmina) {
		// TODO Auto-generated method stub
		SQLQuery query = getSessionTicket().createSQLQuery("SELECT [idMapped],[partNumberSanmina],[partNumberCustomer],[customer],[active] "
				+ "FROM [SFDC_Ticket_Control].[dbo].[MappedPartNumber] "
				+ "WHERE [partNumberSanmina] = '"+ partSanmina + "' AND active = 1").addEntity(MappedPartNumber.class);
		return (MappedPartNumber) query.uniqueResult();
	}

}
