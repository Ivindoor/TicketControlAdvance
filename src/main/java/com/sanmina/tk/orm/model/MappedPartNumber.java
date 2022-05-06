package com.sanmina.tk.orm.model;

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
@Table(name = "MappedPartNumber", schema = "dbo", catalog = "SFDC_Ticket_Control")
@NoArgsConstructor
public class MappedPartNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMapped")
	@Getter
	@Setter
	private Integer idMapped;
	
	@Column(name = "partNumberSanmina")
	@Getter
	@Setter
	private String partNumberSanmina;
	
	@Column(name = "partNumberCustomer")
	@Getter
	@Setter
	private String partNumberCustomer;
	
	@Column(name = "customer")
	@Getter
	@Setter
	private String customer;
	
	@Column(name = "active")
	@Getter
	@Setter
	private Boolean active;
}
