package com.sanmina.tk.orm.dao.skid;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.AbstractDAO;
import com.sanmina.tk.orm.model.skid.PartNumberSkid;

@Repository("PartNumberSkidDao")
@Transactional("txSkid")
public class PartNumberSkidDaoImpl extends AbstractDAO implements PartNumberSkidDao{

	@Override
	public PartNumberSkid findPartByIdPart(Long idPart) {
		// TODO Auto-generated method stub
		Criteria criteria = getSessionSkid().createCriteria(PartNumberSkid.class);
		criteria.add(Restrictions.eq("idPartNumber", idPart));
		return (PartNumberSkid) criteria.uniqueResult();
	}

}
