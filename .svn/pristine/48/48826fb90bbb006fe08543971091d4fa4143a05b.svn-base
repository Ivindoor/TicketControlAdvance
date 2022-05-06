package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Entity
@Table(name="tblCheckedDelivery",schema="dbo" ,catalog="SFDC_Ticket_Control")
public class tblCheckedDelivery {

	
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="idDelivery")
@Getter @Setter private Long idDelivery;
	

@Column(name="Delivery", nullable=true)
@Getter @Setter private String Delivery;


@Column(name="Qty",nullable=true)
@Getter @Setter private Integer Qty;


@Column(name="Status", nullable=true)
@Getter @Setter private Integer Status;

@Column(name="dateCkecked",nullable=true)
@Getter @Setter private Date dateCkecked;


@Column (name="statusReport")
@Getter @Setter private Integer statusReport;


}
