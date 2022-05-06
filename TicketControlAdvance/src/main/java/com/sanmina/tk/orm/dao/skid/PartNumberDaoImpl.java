package com.sanmina.tk.orm.dao.skid;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.AbstractDAO;
import com.sanmina.tk.orm.model.skid.PartNumber;

@Repository("PartNumberDao")
@Transactional("txsManagerSkid")
public class PartNumberDaoImpl extends AbstractDAO implements PartNumberDao {

	@Override
	public void savePartNumber(PartNumber PartNumber) {
		persistSkid(PartNumber);
	}

	@Override
	public void updateSerial(PartNumber PartNumber) {
		getSessionSkid().update(PartNumber);
	}

	@Override
	public void deleteSerial(PartNumber PartNumber) {
		getSessionSkid().delete(PartNumber);
	}

	@Override
	public PartNumber findPartByPartPrint(String partPrint) {
		Criteria criteria= getSessionSkid().createCriteria(PartNumber.class);
		criteria.add(Restrictions.eq("Part_Print",partPrint));
		return (PartNumber) criteria.uniqueResult();
	}

	@Override
	public PartNumber findPartByPart(String partNumber) {
		Criteria criteria= getSessionSkid().createCriteria(PartNumber.class);
		criteria.add(Restrictions.eq("Part_Name",partNumber));
		return (PartNumber) criteria.uniqueResult();
	}
	
	

}
