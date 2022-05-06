package com.sanmina.tk.orm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Coment", schema = "dbo", catalog = "SFDC_Ticket_Control")
public class Coment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id_coment")	
	@Getter @Setter private Long id_coment;//id
	
	@Column(name="coment")
	@Getter @Setter private String  coment;
	
	
	@Column(name="dte")
	@Getter @Setter private Date dte; 

	
	
	
	
	
}
