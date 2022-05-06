package com.sanmina.tk.orm.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Entity
@Table(name="tbl_TypeUser", schema="dbo", catalog="SFDC_Ticket_Control")
public class tbl_TypeUser {


	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idTypeUser")
	@Getter @Setter private Integer idTypeUser ;

	
	
	@Column(name = "TypeUserName")
	@Getter @Setter private String TypeUserName ;

	
	
	
	
	
}
