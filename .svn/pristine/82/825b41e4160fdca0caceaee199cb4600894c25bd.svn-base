package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tblStatusDelivery", schema="dbo", catalog="SFDC_Ticket_Control")
public class tblStatusDelivery {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idStatus")	
	@Getter @Setter private Integer idStatus;	
		

	@Column(name="Status", nullable=true)	
	@Getter @Setter private String Status;	
	
	
	
}
