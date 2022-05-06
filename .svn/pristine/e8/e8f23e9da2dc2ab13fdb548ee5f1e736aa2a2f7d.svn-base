package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="tbl_SiemensConfig", schema="dbo", catalog="SFDC_Ticket_Control")
public class tbl_SiemensConfig {

@Id	
@GeneratedValue(strategy=GenerationType.AUTO)	
@Column(name= "idpartnumber")
@Getter @Setter private Integer idpartnumber;

@Column(name= "partnumber" , nullable=true)
@Getter @Setter private String partnumber;


@Column(name= "changepartnumber", nullable=true )
@Getter @Setter private String changepartnumber;





}
