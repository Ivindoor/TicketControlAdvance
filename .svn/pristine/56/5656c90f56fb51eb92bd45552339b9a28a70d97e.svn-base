package com.sanmina.tk.orm.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.util.Date;


@Entity
@Table(name="Part_Family", schema="dbo", catalog="SFDC_Ticket_Control")
public class Part_Family {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_family")
	@Setter @Getter private Long id_family; 
	
	@Column(name="family")
	@Setter @Getter private String family; 
	
	@Column(name="id_project")
	@Setter @Getter private Long id_project; 
	
	
	@Column(name="palLet_qty")
	@Setter @Getter private Long pallete_qty;
	
	
	@Column(name="dte")
	@Setter @Getter private Date dte;
	
	
	
	
	
	
}
