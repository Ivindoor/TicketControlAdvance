package com.sanmina.tk.orm.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;


@Entity
@Table(name="tbl_CreePCA_Config", schema="dbo", catalog="SFDC_Ticket_Control")
public class tbl_CreePCA_Config {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idPartNumber")
	@Getter @Setter private Integer idPartNumber;
	
	
	@Column(name="PartNumber", nullable=true)
	@Getter @Setter private String PartNumber;
	
	

	@Column(name="ChangePartNumber", nullable=true)
	@Getter @Setter private String ChangePartNumber;
	
	
	
}
