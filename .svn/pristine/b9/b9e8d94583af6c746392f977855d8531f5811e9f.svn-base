package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Entity
@Table(name="tbl_serialesCree", schema="dbo", catalog="SFDC_Ticket_Control")
public class tbl_serialesCree {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)	
@Column(name="Idserial")	
@Getter @Setter private Long IdSerial;

@Column(name="serial")	
@Getter @Setter private String serial;

@Column(name="partnumber")	
@Getter @Setter private String partnumber;


@Column(name="idContainer")	
@Getter @Setter private Integer idContainer;


@Column(name="idticket")	
@Getter @Setter private Integer idticket;


@Column(name="logdate")	
@Getter @Setter private Date logdate;







}
