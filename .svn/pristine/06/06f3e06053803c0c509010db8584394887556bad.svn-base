package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tblInstruction;

@Repository("tblInstructionDao")
@Transactional("txTicket")
public class tblInstructionDaoImpl extends AbstractDAO implements tblInstructionDao {

	@SuppressWarnings("unchecked")
	public List<tblInstruction> findAllInstruction(String project) {
		SQLQuery query = getSessionTicket()
				.createSQLQuery("SELECT [idPoLine],[shippingInstruction],[priority],[po],[poLine],[partNumber],[project]"
						+ ",[partQtyRequired],[partQtyStored],[expiredDate]"
						+ "FROM [SFDC_Ticket_Control].[dbo].[tblInstruction] WHERE project = :project").addEntity(tblInstruction.class);
		query.setParameter("project", project);
		return (List<tblInstruction>) query.list();
	}

}
