package com.sanmina.tk.orm.dao.rmx;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.AbstractDAO;
import com.sanmina.tk.orm.model.rmx.Unit;

@Repository("unitDao")
@Transactional("txTicket")
public class UnitDaoImpl extends AbstractDAO implements UnitDao {

	@Override
	public Unit findUnitBySerial(String serial) {
		// TODO Auto-generated method stub
		SQLQuery query = getSessionTicket()
				.createSQLQuery(
						"SELECT unit_id, unit_purchase_order FROM rmx_2.dbo.unit WHERE unit_serial_number = :serial;")
				.addEntity(Unit.class);
		query.setParameter("serial", serial);
		return (Unit) query.uniqueResult();
	}

}
