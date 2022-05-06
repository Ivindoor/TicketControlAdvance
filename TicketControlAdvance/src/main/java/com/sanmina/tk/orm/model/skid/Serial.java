package com.sanmina.tk.orm.model.skid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "serial", schema = "dbo", catalog = "Skid_Control_V3")
@NoArgsConstructor
public class Serial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_id")
	@Getter
	@Setter
	private Long serial_id;

	@Column(name = "serial_number")
	@Getter
	@Setter
	private String serial_number;

	@Column(name = "serial_rev")
	@Getter
	@Setter
	private String serial_rev;

	@Column(name = "conte_id")
	@Getter
	@Setter
	private Long conte_id;

	@Column(name = "Part_ID")
	@Getter
	@Setter
	private Long Part_ID;
	
	
	@Column(name = "serial_po")
	@Getter
	@Setter
	private String serial_po;

	
	@Column(name = "serial_so")
	@Getter
	@Setter
	private String serial_so;
	
	@Column(name = "Serial_PCAs")
	@Getter
	@Setter
	private String Serial_PCAs;
}