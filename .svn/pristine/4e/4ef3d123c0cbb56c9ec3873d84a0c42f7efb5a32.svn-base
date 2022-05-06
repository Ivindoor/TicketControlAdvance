package com.sanmina.tk.orm.dao.skid;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.AbstractDAO;

import com.sanmina.tk.orm.model.skid.Serial;

@Repository("SerialDao")
@Transactional("txsManagerSkid")
public class SerialDaoImpl extends AbstractDAO implements SerialDao {

	@Override
	public void saveSerial(Serial serial) {
		persistSkid(serial);
	}

	@Override
	public void updateSerial(Serial serial) {
		getSessionSkid().update(serial);
	}

	@Override
	public void deleteSerial(Serial serial) {
		getSessionSkid().delete(serial);
	}

	@Override
	public Serial findSerialBySerial(String serial) {
		Criteria criteria = getSessionSkid().createCriteria(Serial.class);
		criteria.add(Restrictions.eq("serial_number", serial));
		return (Serial) criteria.uniqueResult();
	}

}
