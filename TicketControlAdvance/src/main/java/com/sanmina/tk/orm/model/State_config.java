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
@Table(name="Sate_config" , schema="dbo", catalog="SFDC_Ticket_Control")
public class State_config {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id_State")
@Getter @Setter private Long id_State; 	
	
	
@Column(name="Name")
@Getter @Setter private String Name; 	


@Column(name="Description", nullable=true)
@Getter @Setter private String Description; 	






}
