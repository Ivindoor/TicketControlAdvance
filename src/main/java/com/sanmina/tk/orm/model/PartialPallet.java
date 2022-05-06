package com.sanmina.tk.orm.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PartialPallet {
	
	@Id
	private String ticket;

	private String container;
	
	private String partNumber;
	
	private Integer qty;
}
