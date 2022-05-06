package com.sanmina.tk.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "zpl", schema = "dbo", catalog = "SFDC_Ticket_Control")
public class ZPL {
	@Id
	@Column(name = "id_zpl", unique = true, nullable = false)
	@Getter
	@Setter
	private long id;

	@Column(name = "Descripcion")
	@Getter
	@Setter
	private String descripcion;

	@Column(name = "ZPL")
	@Getter
	@Setter
	private String zpl;

	@Column(name = "Station")
	@Getter
	@Setter
	private String station;

	@Column(name = "ipzebra")
	@Getter
	@Setter
	private String ip;

	@Column(name = "id_project", nullable = false)
	@Getter
	@Setter
	private long idProject;

}
