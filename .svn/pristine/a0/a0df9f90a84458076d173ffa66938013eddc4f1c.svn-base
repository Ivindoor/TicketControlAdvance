package com.sanmina.tk.orm.model.skid;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Part_Number", schema = "dbo", catalog = "Skid_Control_V3")
@NoArgsConstructor
public class PartNumber{

	@Id
	@Column(name = "Part_ID", unique = true, nullable = false, updatable = false)
	@Getter
	@Setter
	private Long Part_ID;

	@Column(name = "Part_Name")
	@Getter
	@Setter
	private String Part_Name;

	@Column(name = "Part_Customer")
	@Getter
	@Setter
	private String Part_Customer;

	@Column(name = "Part_Print")
	@Getter
	@Setter
	private String Part_Print;

	@Column(name = "Part_qty_container")
	@Getter
	@Setter
	private Integer Part_qty_container;
	
	@Column(name = "proyecto_id")
	@Getter
	@Setter
	private Long proyecto_id;
	
	@Column(name = "kit_verify")
	@Getter
	@Setter
	private Boolean kit_verify;
	
	@Column(name = "Zpl_File")
	@Getter
	@Setter
	private String Zpl_File;
	
	@Column(name = "pca_ref")
	@Getter
	@Setter
	private String pca_ref;
	
	@Column(name = "Type_id")
	@Getter
	@Setter
	private Long Type_id;
	
	
	@Column(name = "Mezcla")
	@Getter
	@Setter
	private Boolean Mezcla;
	
	@Column(name = "Final_validacion")
	@Getter
	@Setter
	private String Final_validacion;
	
	@Column(name = "Valida_PO")
	@Getter
	@Setter
	private Integer Valida_PO;
	
	@Column(name = "Formato_Hoja")
	@Getter
	@Setter
	private Integer Formato_Hoja;
	
	@Column(name = "Enable")
	@Getter
	@Setter
	private Boolean Enable;

}