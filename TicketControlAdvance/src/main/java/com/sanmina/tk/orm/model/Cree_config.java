package com.sanmina.tk.orm.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Cree_config", schema = "dbo", catalog = "SFDC_Ticket_Control")
public class Cree_config {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="idConfig")	
	@Getter @Setter private Long idConfig;
	
	@Column(name="id_project")
	@Getter @Setter private Long  id_project;
	
	
	@Column(name="Model")
	@Getter @Setter private String Model; 

	
	
	@Column(name="Family")
	@Getter @Setter private String Family;
	
	
	@Column(name="Scaning")
	@Getter @Setter private String Scanning;
	
	
	
	
	
	
}
