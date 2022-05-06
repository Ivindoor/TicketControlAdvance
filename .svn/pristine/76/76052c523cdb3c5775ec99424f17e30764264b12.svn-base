package com.sanmina.tk.orm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblInstruction", schema = "dbo", catalog = "SFDC_Ticket_Control")
@NoArgsConstructor
public class tblInstruction {

	@Id
	@Getter
	@Setter
	private Integer idPoLine;
	
	@Getter
	@Setter
	private String shippingInstruction;
	
	@Getter
	@Setter
	private Integer priority;
	
	@Getter
	@Setter
	private String po;
	
	@Getter
	@Setter
	private String poLine;
	
	@Getter
	@Setter
	private String partNumber;
	
	@Getter
	@Setter
	private Integer partQtyRequired;
	
	@Getter
	@Setter
	private Integer partQtyStored;
	
	@Getter
	@Setter
	private Date expiredDate;
	
	@Getter
	@Setter
	private String project;
}
