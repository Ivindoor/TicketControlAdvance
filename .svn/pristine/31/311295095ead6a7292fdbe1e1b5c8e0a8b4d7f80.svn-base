package com.sanmina.tk.orm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_UserLogin", schema = "dbo", catalog = "SFDC_Ticket_Control")
public class Users implements Serializable {

	private static final long serialVersionUID = -6672567653472919735L;
	public static final byte USR_LEVEL_IT = 4;
	public static final byte USR_LEVEL_ADMINISTRATOR = 3;
	public static final byte USR_LEVEL_WORKER = 2;
	public static final byte USR_LEVEL_OBSERVER = 1;
	public static final byte USR_LEVEL_SUPPORT = 0;

	@Id
	@Column(name = "idUser")
	@Getter
	@Setter
	private Integer id;
	@Column(name = "Nombre")
	@Getter
	@Setter
	private String nombre;
	@Column(name = "Apaterno")
	@Getter
	@Setter
	private String aPaterno;
	@Column(name = "Amaterno")
	@Getter
	@Setter
	private String aMaterno;
	@Column(name = "NoEmpleado")
	@Getter
	@Setter
	private String noEmpleado;
	@Column(name = "Password")
	@Getter
	@Setter
	private String password;
	@Column(name = "Puesto")
	@Getter
	@Setter
	private String puesto;
	@Column(name = "Departamento")
	@Getter
	@Setter
	private String departamento;
	@Column(name = "Proyecto")
	@Getter
	@Setter
	private String proyecto;
	@Column(name = "Turno")
	@Getter
	@Setter
	private Long turno;
	@Column(name = "Status")
	@Getter
	@Setter
	private Boolean status;
	@Column(name = "FechaAlta")
	@Getter
	@Setter
	private Date fechaAlta;
	@Column(name = "UniversalLogin")
	@Getter
	@Setter
	private String universalLogin;
	@Column(name = "Level")
	@Getter
	@Setter
	private Integer level;
	@Column(name = "LevelTicket")
	@Getter
	@Setter
	private Integer levelTicket;
	@Column(name = "idTypeUser")
	@Getter
	@Setter
	private Integer type;
	@Column(name = "LevelSkid")
	@Getter
	@Setter
	private Integer levelSkid;
}
