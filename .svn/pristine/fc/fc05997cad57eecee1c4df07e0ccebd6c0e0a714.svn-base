package com.sanmina.tk.orm.model.skid;

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
@Table(name = "contenedor", schema = "dbo", catalog = "Skid_Control_V3")
@NoArgsConstructor
public class ContainerSkid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "conte_id", unique = true, nullable = false, updatable = false)
	@Getter
	@Setter
	private Long idContainer;
	
	@Column(name = "conte_acum")
	@Getter
	@Setter
	private Integer qtyContainer;
	
	@Column(name = "conte_date")
	@Getter
	@Setter
	private String dte;
}
