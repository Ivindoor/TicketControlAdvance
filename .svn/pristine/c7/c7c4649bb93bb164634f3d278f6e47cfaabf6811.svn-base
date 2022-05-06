package com.sanmina.tk.orm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblShippingInstruction", schema = "dbo", catalog = "SFDC_Ticket_Control")
@NoArgsConstructor
public class ShippingInstruction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idShippingInstruction")
	@Getter
	@Setter
	private Integer idShippingInstruction;

	@Column(name = "shippingInstruction")
	@Getter
	@Setter
	private String shippingInstruction;
	
	@Column(name = "qtyPo")
	@Getter
	@Setter
	private Integer qtyPo;
	
	@Column(name = "startDate")
	@Getter
	@Setter
	private Date startDate;
	
	@Column(name = "finishDate")
	@Getter
	@Setter
	private Date finishDate;
	
	@Column(name = "active")
	@Getter
	@Setter
	private Boolean active;
	
	@Column(name = "priority")
	@Getter
	@Setter
	private Integer priority;
	
	@Column(name = "expiredDate")
	@Getter
	@Setter
	private Date expiredDate;
	
	@Column(name = "project")
	@Getter
	@Setter
	private String project;

}