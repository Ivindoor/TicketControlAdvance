package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name="tbl_TicketDeleted", schema="dbo", catalog="SFDC_Ticket_Control")
public class tbl_TicketDeleted  {

	@Id
	@Column(name="id_ticket")
	@Getter @Setter private long id_ticket;
	
	@Column(name="Ticket")
	@Getter @Setter private String Ticket;
	
	@Column(name="dteDeleted")
	@Getter @Setter private Date dteDeleted;

	@Column(name="UserName")
	@Getter @Setter private String UserName;
	
	@Column(name="idProject")
	@Getter @Setter private long idProject;
	
	@Column(name="Description")
	@Getter @Setter private String Description;
}
