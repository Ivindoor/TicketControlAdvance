package com.sanmina.tk.orm.model;

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
@Table(name = "tblPoLine", schema = "dbo", catalog = "SFDC_Ticket_Control")
@NoArgsConstructor
public class PoLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPoLine")
	@Getter
	@Setter
	private Integer idPoLine;

	@Column(name = "poLine")
	@Getter
	@Setter
	private String poLine;

	@Column(name = "idPo")
	@Getter
	@Setter
	private Integer idPo;

	@ManyToOne
	@JoinColumn(name = "idPo", nullable = false, insertable = false, updatable = false)
	@Getter
	@Setter
	private Po po;

	@Column(name = "partNumber")
	@Getter
	@Setter
	private String partNumber;

	@Column(name = "partQtyRequired")
	@Getter
	@Setter
	private Integer partQtyRequired;

	@Column(name = "partQtyStored")
	@Getter
	@Setter
	private Integer partQtyStored;

	@Column(name = "active")
	@Getter
	@Setter
	private Boolean active;
}
