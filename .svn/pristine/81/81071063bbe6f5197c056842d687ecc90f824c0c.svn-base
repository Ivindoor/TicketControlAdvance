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
@Table(name = "Part_Number", schema = "dbo", catalog = "Skid_Control_V3")
@NoArgsConstructor
public class PartNumberSkid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Part_ID")
	@Getter
	@Setter
	private Long idPartNumber;
	
	@Column(name = "Part_Name")
	@Getter
	@Setter
	private String partNumber;
	
	@Column(name = "Part_Print")
	@Getter
	@Setter
	private String partNumberPrint;
	
}
