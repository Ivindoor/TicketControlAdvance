package com.sanmina.tk.orm.dao.skid;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.AbstractDAO;
import com.sanmina.tk.orm.model.skid.SerialSkid;

@Repository("SerialSkidDao")
@Transactional("txSkid")
public class SerialSkidDaoImpl extends AbstractDAO implements SerialSkidDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<SerialSkid> findSerialByContainer(Long container) {
		// TODO Auto-generated method stub
		Criteria criteria = getSessionSkid().createCriteria(SerialSkid.class);
		criteria.add(Restrictions.eq("idContainer", container));
		return (List<SerialSkid>) criteria.list();
	}

	@Override
	public SerialSkid findSerialBySerial(String serial) {
		// TODO Auto-generated method stub
		Criteria criteria = getSessionSkid().createCriteria(SerialSkid.class);
		criteria.add(Restrictions.eq("serial", serial));
		return (SerialSkid) criteria.uniqueResult();
	}

}
