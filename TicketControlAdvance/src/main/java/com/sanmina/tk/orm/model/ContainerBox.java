package com.sanmina.tk.orm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Container_Box",schema = "dbo", catalog="SFDC_Ticket_Control")
public class ContainerBox {

	@Id
	@Column(name="id")
	@Getter @Setter private Integer id;
	
	@Column(name="id_box", nullable = false)
	@Getter @Setter private long boxId;
	
	@Column(name="id_containers", nullable = false)
	@Getter @Setter private int containersId;
	
	public ContainerBox(){}
	
	public ContainerBox(long boxId, int containersId){
		this.boxId = boxId;
		this.containersId = containersId;
	}
}
