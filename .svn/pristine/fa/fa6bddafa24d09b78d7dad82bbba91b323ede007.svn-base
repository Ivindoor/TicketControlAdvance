package com.sanmina.tk.orm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Box", schema = "dbo", catalog = "SFDC_Ticket_Control")
public class Box {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id_box")	
	@Getter @Setter private Long idBox;
	
	@Column(name="NumberBox")
	@Getter @Setter private String  boxNumber;
	
	@Column(name="delivery")
	@Getter @Setter private String delivery;
	
	@Column(name="qty")
	@Getter @Setter private Long qty = 0L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_ticket")
	@Getter @Setter private Ticket ticket;
	
	@Column(name="printCount",nullable=true)
	@Getter @Setter private int printcount;
	
	@Column(name="idUser", nullable=true)
	@Getter @Setter private int userId;
	
	@Column(name="printCountLabel", nullable = true)
	@Getter @Setter private int printlabelCount;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name ="Container_Box", joinColumns= { 
			   @JoinColumn(name="id_box", nullable = false, updatable = false) },
			   inverseJoinColumns = { @JoinColumn(name="id_containers", nullable = false, updatable = false) })
	@Getter @Setter private Set<Container> containers = new HashSet<Container>();
	
	@Transient
	private String partNumber;
	@Transient
	private String po;
	
	/**
	 * Returns true if the delivery number 
	 * is already set.
	 * @return A true boolean if the delivery number 
	 * has any character long and is not null.
	 */
	public boolean hasDelivery(){
		return delivery != null && delivery.length() > 0;
	}
	
	public String getPartNumber() {
		if(this.partNumber == null || this.partNumber.length() == 0) {
			if( this.containers != null && this.containers.size() > 0) {
				for(Container container : this.containers) {
					// return the first one
					this.partNumber = container.getPartNumber();
					return this.partNumber;
				}
			}
		}
		return this.partNumber;
	}
	
	public String getPo() {
		if(po == null || po.length() == 0) {
			if( containers != null && containers.size() > 0) {
				for(Container container : containers) {
					// return the first one
					po = container.getPo();
					return po;
				}
			}
		}
		return po;
	}
	
	public void addContainer( Container container ){
		container.addBox(this);
		this.containers.add(container);
	}
	
	public void removeContainer( Container container ) {
		container.removeBox(this);
		this.containers.remove(container);
	}
}
