package com.sanmina.tk.orm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblPo", schema = "dbo", catalog = "SFDC_Ticket_Control")
@NoArgsConstructor
public class Po {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPo")
	@Getter
	@Setter
	private Integer idPo;

	@Column(name = "po")
	@Getter
	@Setter
	private String po;
	
	@Column(name = "idShippingInstruction")
	@Getter
	@Setter
	private Integer idShippingInstruction;
	
	@ManyToOne
	@JoinColumn(name = "idShippingInstruction", nullable = false, insertable = false, updatable = false)
	@Getter
	@Setter
	private ShippingInstruction shippingInstruction;
	
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
	
}