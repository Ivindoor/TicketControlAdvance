package com.sanmina.tk.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class NonSerializedGenbandPO {

	@Id
	@Getter	@Setter
	@Column( name ="Customer_po" )
	private String po;

	@Getter	@Setter	private Long qty;

}
