package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.ShippingInstruction;

@Repository("shippingInstructionDao")
@Transactional("txTicket")
public class ShippingInstructionDaoImpl extends AbstractDAO implements ShippingInstructionDao {

	@Override
	public void saveShippingInstruction(ShippingInstruction instruction) {
		persistTicket(instruction);
	}

	@Override
	public void deleteShippingInstructionByShippingInstruction(ShippingInstruction instruction) {
		getSessionTicket().delete(instruction);
	}

	@Override
	public void updateShippingInstruction(ShippingInstruction instruction) {
		getSessionTicket().update(instruction);
	}

	@SuppressWarnings("unchecked")
	public List<ShippingInstruction> findAllShippingInstruction() {
		Criteria criteria = getSessionTicket().createCriteria(ShippingInstruction.class);
		return (List<ShippingInstruction>) criteria.list();
	}

	@Override
	public ShippingInstruction findPriorityShippingInstruction() {
		SQLQuery query = getSessionTicket().createSQLQuery("SELECT"
				+ " [idShippingInstruction],[shippingInstruction],[qtyPo],[startDate],[finishDate],[active],[priority]"
				+ "from [SFDC_Ticket_Control].[dbo].[tblShippingInstruction]" + "where active = 1 order by [priority]")
				.addEntity(ShippingInstruction.class);
		return (ShippingInstruction) query.uniqueResult();
	}

	public ShippingInstruction findInstruccionByIdShippingInstruction(int idShippingInstruction) {
		Criteria criteria = getSessionTicket().createCriteria(ShippingInstruction.class);
		criteria.add(Restrictions.eq("idShippingInstruction", idShippingInstruction));
		return (ShippingInstruction) criteria.uniqueResult();
	}
	
	public ShippingInstruction findInstruccionByShippingInstruction(String shippingInstruction) {
		Criteria criteria = getSessionTicket().createCriteria(ShippingInstruction.class);
		criteria.add(Restrictions.eq("shippingInstruction", shippingInstruction));
		return (ShippingInstruction) criteria.uniqueResult();
	}

}