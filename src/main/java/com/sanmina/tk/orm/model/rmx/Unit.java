package com.sanmina.tk.orm.model.rmx;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unit", schema = "dbo", catalog = "rmx_2")
@NoArgsConstructor
public class Unit {
	
	@Id
	@Column(name = "unit_id")
	@Getter
	@Setter
	private Integer idUnit;

	@Column(name = "unit_purchase_order")
	@Getter
	@Setter
	private String purchaseOrder;
}
