package com.sanmina.tk.orm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="tbl_DeliveriesDeleted" ,schema="dbo", catalog="SFDC_Ticket_Control" )
public class tbl_DeliveriesDeleted {

	@Id
	@Column(name="Delivery", nullable=true)
	@Getter @Setter private String Delivery; 
	
	
	@Column(name="IdTicket", nullable=true)
	@Getter @Setter private Integer IdTicket; 
	
	
	@Column(name="id_box", nullable=true)
	@Getter @Setter private Integer id_box; 
	
	
	
	@Column(name="NumberBox", nullable=true)
	@Getter @Setter private Integer NumberBox; 
	
	
	@Column(name="Qty", nullable=true)
	@Getter @Setter private Integer Qty; 
	
	
	@Column(name="id_Container", nullable=true)
	@Getter @Setter private Integer id_Container; 
	
	
	@Column(name="IdUser", nullable=true)
	@Getter @Setter private Integer IdUser; 
	
	
	
	
	
}
