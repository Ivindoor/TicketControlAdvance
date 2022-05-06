package com.sanmina.tk.orm.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import lombok.Setter;
import lombok.Getter;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "Part_Number", schema = "dbo", catalog = "SFDC_Ticket_Control")
public class Part_Number {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_partnumber")
	@Getter @Setter private Long id_partnumber;

	@Column(name = "Part_number")
	@Getter @Setter private String Part_number;

	@Column(name="id_family")
	@Getter @Setter private Long id_family;

	@Column(name="container_qty")
	@Getter @Setter private Long container_qty;

	@Column(name="enabled")
	@Getter @Setter private Long enabled;
	
	@Column(name="dte")
	@Setter @Getter private Date dte;

	@Column(name="Part_Print", nullable=true)
	@Setter @Getter private String Part_Print;

}
