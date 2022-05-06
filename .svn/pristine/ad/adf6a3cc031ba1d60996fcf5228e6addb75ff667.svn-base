package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tblInstructionReport;

@Repository("tblInstructionReportDao")
@Transactional("txTicket")
public class tblInstructionReportDaoImpl extends AbstractDAO implements tblInstructionReportDao {

	@SuppressWarnings("unchecked")
	public List<tblInstructionReport> findAllInstructionReport() {
		SQLQuery query = getSessionTicket().createSQLQuery("SELECT "
				+ "[priority],[shippingInstruction],[po],[poLine],[partNumber],[partQtyRequired],[partQtyStored],[expiredDate],[active],[project] "
				+ "FROM [SFDC_Ticket_Control].[dbo].[tblInstructionReport2]").addEntity(tblInstructionReport.class);
		return (List<tblInstructionReport>) query.list();
	}

}
