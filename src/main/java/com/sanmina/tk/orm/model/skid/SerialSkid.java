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
public class SerialSkid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_id")
	@Getter
	@Setter
	private Long idSerial;
	
	@Column(name = "serial_number")
	@Getter
	@Setter
	private String serial;
	
	@Column(name = "conte_id")
	@Getter
	@Setter
	private Long idContainer;
	
	
	@Column(name = "Part_ID")
	@Getter
	@Setter
	private Long idPartNumber;
	
}
