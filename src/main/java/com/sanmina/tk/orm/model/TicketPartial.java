package com.sanmina.tk.orm.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TicketPartial {

	@Id
	private String ticket;
	
	private String pallet;
	
	private String partNumber;
	
	private Integer qty;
	
	private String description;
}
