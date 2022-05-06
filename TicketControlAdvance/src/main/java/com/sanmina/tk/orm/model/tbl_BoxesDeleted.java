package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name="tbl_BoxesDeleted", schema="dbo", catalog="SFDC_Ticket_Control" )
public class tbl_BoxesDeleted {

	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id_Box", nullable=true)	
@Getter @Setter private Integer id_Box;	


@Column(name="id_Container",nullable=true)	
@Getter @Setter private Integer id_Container;	






}
