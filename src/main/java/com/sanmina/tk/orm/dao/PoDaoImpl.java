package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Po;

@Repository("poDao")
@Transactional("txTicket")
public class PoDaoImpl extends AbstractDAO implements PoDao {

	@Override
	public void savePo(Po po) {
		persistTicket(po);
	}

	@Override
	public void deletePoByPo(Po po) {
		getSessionTicket().delete(po);
	}

	@Override
	public void updatePo(Po po) {
		getSessionTicket().update(po);
	}

	@SuppressWarnings("unchecked")
	public List<Po> findAllPo() {
		Criteria criteria = getSessionTicket().createCriteria(Po.class);
		return (List<Po>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Po> findPriorityPo() {
		SQLQuery query = getSessionTicket().createSQLQuery("SELECT "
				+ "[idPo],[po],Tp.[idShippingInstruction],[qtyRequired],[qtyStored],Tp.[startDate],Tp.[finishDate],Tp.[active],Tp.[expiredDate]"
				+ " FROM [SFDC_Ticket_Control].[dbo].[tblPo] Tp "
				+ "INNER JOIN [SFDC_Ticket_Control].[dbo].[tblShippingInstruction] Ts "
				+ "ON Tp.[idShippingInstruction] = Ts.[idShippingInstruction]"
				+ "Where Ts.[priority] = (select min([priority]) from [SFDC_Ticket_Control].[dbo].[tblShippingInstruction]) and Tp.active = 1").addEntity(Po.class);
		return (List<Po>)query.list();
	}

	@Override
	public Po findPoByIdPo(int idPo) {
		Criteria criteria = getSessionTicket().createCriteria(Po.class);
		criteria.add(Restrictions.eq("idPo",idPo));
		return (Po) criteria.uniqueResult();
	}
	
	@Override
	public Po findPoByPo(String po) {
		Criteria criteria = getSessionTicket().createCriteria(Po.class);
		criteria.add(Restrictions.eq("po", po));
		return (Po) criteria.uniqueResult();
	}
	
	@Override
	public Po findPoByIdShippingInstruction(int idShippingInstruction) {
		Criteria criteria = getSessionTicket().createCriteria(Po.class);
		criteria.add(Restrictions.eq("idShippingInstruction",idShippingInstruction));
		return (Po) criteria.uniqueResult();
	}

}
