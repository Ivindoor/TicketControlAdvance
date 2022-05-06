package com.sanmina.tk.orm.model.skid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Proyecto", schema = "dbo", catalog = "Skid_Control_V3")
@NoArgsConstructor

public class Proyecto {
	
	@Id
	@Column(name = "proyecto_id", unique = true, nullable = false, updatable = false)
	@Getter
	@Setter
	private Integer proyecto_id;

	@Column(name = "proyecto_name")
	@Getter
	@Setter
	private String proyecto_name;

	@Column(name = "Enable")
	@Getter
	@Setter
	private Boolean Enable;

	@Column(name = "Proyect_base")
	@Getter
	@Setter
	private String Proyect_base;

	@Column(name = "IP_Base")
	@Getter
	@Setter
	private String IP_Base;
	
	
	

}
