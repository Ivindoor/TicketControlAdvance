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
@Table(name="tblShippingConfigCree",schema="dbo", catalog="SFDC_Ticket_Control")
public class tblShippingConfigCree {

	
	
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="idPartNumber")	
@Getter @Setter private Long idPartNumber;	
	

@Column(name="PartNumber", nullable=true)	
@Getter @Setter private String PartNumber;	


@Column(name="joindate")	
@Getter @Setter private String joindate;	


@Column(name="status")	
@Getter @Setter private Boolean status;	



}
