package com.sanmina.tk.orm.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Containers", schema = "dbo", catalog = "SFDC_Ticket_Control")
public class Container implements java.io.Serializable{

	private static final long serialVersionUID = -2958912216851626690L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_containers", unique = true)
	@Getter @Setter private long idContainers;

	@Column(name="id_ticket")
	@Getter @Setter private long idTicket;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ticket", nullable = false, insertable=false, updatable=false)
	@Getter @Setter private Ticket ticket;
	
	@Column(name = "container", nullable = false, length = 50)
	@Getter @Setter private String container;
	
	@Column(name = "part_number", nullable = false, length = 50)
	@Getter @Setter private String partNumber;
	
	@Column(name = "qty", nullable = false, precision = 18, scale = 0)
	@Getter @Setter private long qty;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dte", nullable = false, length = 23)
	@Getter @Setter private Date dte;

	@Column(name = "PO", nullable = true, length = 50)
	@Getter @Setter private String po;
	
	@Column(name="status")
	@Getter @Setter private int Status;
	
	@Column(name="datafileCree", nullable = false)
	@Getter @Setter private int datafileCree;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="containers", cascade=CascadeType.ALL)
	@Getter @Setter private Set<Box> boxes;
	
//	public Container() {}
//
//	public Container(long idContainers, Ticket tickets, String container,
//			String partNumber, long qty, Date dte, String po,int status) {
//		this.idContainers = idContainers;
//		this.ticket = tickets;
//		this.container = container;
//		this.partNumber = partNumber;
//		this.qty = qty;
//		this.dte = dte;
//		this.po = po;
//		this.Status = status;
//	}
	

	public void addBox(Box box) {
		this.boxes.add(box);
		
	}

	public void removeBox(Box box) {
		this.boxes.remove(box);
	}
}
