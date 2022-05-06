package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name="tbl_EchoStarValid_Config", schema="dbo", catalog="SFDC_Ticket_Control")
public class tbl_EchoStarValid_Config {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdPartNumber")	
	@Getter @Setter private Integer IdPartNumber;	
	
	
	@Column(name="PartNumber")	
	@Getter @Setter private String PartNumber;	
	
	
	
	
}



