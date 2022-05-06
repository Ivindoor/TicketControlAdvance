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
@Table(name = "contenedor", schema = "dbo", catalog = "Skid_Control_V3")
@NoArgsConstructor
public class Container implements java.io.Serializable {

	private static final long serialVersionUID = 3943974146595603051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "conte_id", unique = true, nullable = false, updatable = false)
	@Getter
	@Setter
	private Long conte_id;

	@Column(name = "conte_acum")
	@Getter
	@Setter
	private Integer conte_acum;

	@Column(name = "conte_status")
	@Getter
	@Setter
	private Integer conte_status;

	@Column(name = "conte_print")
	@Getter
	@Setter
	private Integer conte_print;

	@Column(name = "conte_date")
	@Getter
	@Setter
	private String conte_date;
	
	@Column(name = "user_id")
	@Getter
	@Setter
	private Integer user_id;
	
	@Column(name = "proyecto_id")
	@Getter
	@Setter
	private Integer proyecto_id;

}