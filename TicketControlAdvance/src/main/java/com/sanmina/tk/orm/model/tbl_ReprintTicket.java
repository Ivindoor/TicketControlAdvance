package com.sanmina.tk.orm.model;
import  javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Table(name="tbl_ReprintTicket", schema="dbo", catalog="SFDC_Ticket_Control")
public class tbl_ReprintTicket {

@Id	
@GeneratedValue(strategy=GenerationType.AUTO)	
@Column(name="idZpl")
@Getter @Setter private  Integer idZpl;


@Column(name="ZplPrinted", nullable=true)
@Getter @Setter private  String ZplPrinted;



@Column(name="IpZebra", nullable=true)
@Getter @Setter private  String IpZebra;


@Column(name="IdTicket")
@Getter @Setter private  Integer IdTticket;


@Column(name="IdProject")
@Getter @Setter private  Integer IdProject;




}
