package com.sanmina.tk.orm.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Data_Base",schema="dbo", catalog="SFDC_Ticket_Control")
public class Data_Base {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="db_id")	
	@Getter @Setter private Long db_id;
	
	
	@Column(name="Data_base")
	@Getter @Setter private String  Data_base;
	
	@Column(name="dte")
	@Getter @Setter private Date dte;
	
	
	
	
	
	
	

}
