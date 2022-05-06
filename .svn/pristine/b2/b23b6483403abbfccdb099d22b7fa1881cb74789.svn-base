package com.sanmina.tk.orm.model;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.GenerationType;

import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name="Serie_Ticket", schema="dbo", catalog="SFDC_Ticket_Control" )
public class Serie_Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_serie")
	@Getter @Setter Long id_serie;
	
	
	@Column(name="serie_ticket")
	@Getter @Setter Long serie_ticket;
	
	
	
	
}
