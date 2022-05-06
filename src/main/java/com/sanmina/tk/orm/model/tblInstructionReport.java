package com.sanmina.tk.orm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblInstructionReport2", schema = "dbo", catalog = "SFDC_Ticket_Control")
@NoArgsConstructor
public class tblInstructionReport {
	
	public tblInstructionReport(String priority, String shippingInstruction, String po, String poLine,
			String partNumber, String partQtyRequired, Integer partQtyStored, String active, String expiredDate, String project) {
		super();
		this.priority = priority;
		this.shippingInstruction = shippingInstruction;
		this.po = po;
		this.poLine = poLine;
		this.partNumber = partNumber;
		this.partQtyRequired = partQtyRequired;
		this.partQtyStored = partQtyStored;
		this.active = active;
		this.expiredDate = expiredDate;
		this.project = project;
	}

	@Getter
	@Setter
	private String priority;
	
	@Getter
	@Setter
	private String shippingInstruction;
	
	@Getter
	@Setter
	private String po;
	
	@Id
	@Getter
	@Setter
	private String poLine;
	
	@Getter
	@Setter
	private String partNumber;
	
	@Getter
	@Setter
	private String partQtyRequired;
	
	@Getter
	@Setter
	private Integer partQtyStored;
	
	@Getter
	@Setter
	private String active;
	
	@Getter
	@Setter
	private String expiredDate;
	
	@Getter
	@Setter
	private String project;
}
