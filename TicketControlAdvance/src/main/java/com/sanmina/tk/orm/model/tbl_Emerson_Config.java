package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="tbl_Emerson_Config", schema="dbo", catalog="SFDC_Ticket_Control")

public class tbl_Emerson_Config {

 @Id
@GeneratedValue(strategy=GenerationType.AUTO)	
@Column(name="idPart_Number") 
@Getter @Setter private Integer idPart_Number;	
	
 
 @Column(name="Part_Number") 
 @Getter @Setter private String Part_Number;	
 	
 
 
}
