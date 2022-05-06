package com.sanmina.tk.orm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Tickets", schema = "dbo", catalog = "SFDC_Ticket_Control")
@NoArgsConstructor
public class Ticket implements java.io.Serializable {

	public Ticket(Project project, long qtyContainers) {
		super();
		this.project = project;
		this.qtyContainers = qtyContainers;
	}

	public static final String STATE_PRODUCTION_FLOOR = "1";
	public static final String STATE_SHIPMENTS = "2";
	public static final String STATE_LOADING = "3";
	public static final String STATE_SHIPPED = "4";
	public static final String STATE_PURGED = "5";

	@Id
	@Column(name = "id_ticket", unique = true, nullable = false, updatable = false)
	@Getter
	@Setter
	private long idTicket;

	@Column(name = "id_project")
	@Getter
	@Setter
	private long projectId;

	@ManyToOne
	@JoinColumn(name = "id_project", nullable = false, insertable = false, updatable = false)
	@Getter
	@Setter
	private Project project;

	@Column(name = "ticket")
	@Getter
	@Setter
	private String ticket;

	@Column(name = "qty_containers")
	@Getter
	@Setter
	private long qtyContainers;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dte", nullable = false, length = 23)
	@Getter
	@Setter
	private Date dte;

	@Column(name = "enabled")
	@Getter
	@Setter
	private String enabled;

	@Column(name = "posicion")
	@Getter
	@Setter
	private Long posicion;

	@Column(name = "id_so")
	@Getter
	@Setter
	private Long idSo;

	@Column(name = "id_coment")
	@Getter
	@Setter
	private Long idComent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket")
	@Getter
	@Setter
	private Set<Container> containerses = new HashSet<Container>(0);

	@OneToMany(mappedBy = "ticket")
	@Getter
	@Setter
	private Set<Box> boxes = new HashSet<Box>(0);

	/***
	 * Total sum of the qty of all containers of this ticket. *
	 * 
	 * @return The total sum of the qty of all containers.
	 */
	public int totalContainersTotalQuantity() {
		int total = 0;
		for (Container container : getContainerses()) {
			total += container.getQty();
		}
		return total;
	}

	private static final long serialVersionUID = 4451332571968726139L;

	public Set<Container> parentSerials() {
		Set<Container> parentSerials = new HashSet<Container>();
		for (Container container : getContainerses()) {
			String containerName = container.getContainer();
			try {
				Long.parseLong(containerName);
				parentSerials.add(container);
			} catch (Exception nfe) {
			}
		}
		return parentSerials;
	}
}
