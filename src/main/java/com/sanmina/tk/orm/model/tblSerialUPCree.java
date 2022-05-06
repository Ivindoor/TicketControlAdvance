package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name="tblSerialUPCree", schema="dbo",catalog="SFDC_Ticket_Control")
public class tblSerialUPCree {


@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="idSerialUPC")
@Getter @Setter private Integer idSerialUPC; 


@Column(name="idPartNumber",nullable=true)
@Getter @Setter private Integer idPartNumber; 

@Column(name="serial",nullable=true)
@Getter @Setter private String  serial; 


@Column(name="Joindate")
@Getter @Setter private Date  Joindate; 




}
