package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import java.util.Date;


@Entity
@Table(name="tbl_UserLogin", schema="dbo", catalog="SFDC_Ticket_Control")
public class tbl_UserLogin {

	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="idUser")
@Getter @Setter private Integer idUser;
	
@Column(name="Nombre", nullable=true)
@Getter @Setter private String Nombre;


@Column(name="Apaterno",nullable=true)
@Getter @Setter private String Apaterno;



@Column(name="Amaterno", nullable=true)
@Getter @Setter private String Amaterno;


@Column(name="NoEmpleado", nullable=true)
@Getter @Setter private String NoEmpleado ;


@Column(name="Password", nullable=true)
@Getter @Setter private String Password;

@Column(name="Puesto", nullable=true)
@Getter @Setter private String Puesto;


@Column(name="Departamento", nullable=true)
@Getter @Setter private String Departamento;


@Column(name="Proyecto", nullable=true)
@Getter @Setter private String Proyecto;


@Column (name="Turno", nullable=true)
@Getter @Setter private Long Turno;


@Column (name="Satus")
@Getter @Setter private Boolean Status;


@Column (name="FechaAlta")
@Getter @Setter private Date FechaAlta;


@Column (name="UniversalLongin",nullable=true)
@Getter @Setter private String UniversalLongin;


//p
@Column (name="[Level]",nullable=true)
@Getter @Setter private Integer Level;

//p
@Column (name="LevelTicket",nullable=true)
@Getter @Setter private Integer  LevelTicket;


@Column (name="Type",nullable=true)
@Getter @Setter private String Type;


}
