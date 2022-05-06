package com.sanmina.tk.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Project")//, schema = "dbo", catalog = "SFDC_Ticket_Control")
public class Project implements java.io.Serializable {

	public Project(String projectName) {
		super();
		this.projectName = projectName;
	}

	@Id
	@Column(name = "id_project", unique = true, nullable = false, precision = 18, scale = 0)
	@Getter @Setter private long idProject;
	
	@Column(name = "project_name", nullable = false, length = 50)
	@Getter @Setter private String projectName;
	
	@Column(name = "pallet_valid", nullable = false, precision = 18, scale = 0)
	@Getter @Setter private long palletValid;

	public Project() {}

	public Project(long idProject, String projectName, long palletValid) {
		this.idProject = idProject;
		this.projectName = projectName;
		this.palletValid = palletValid;
	}
	
	private static final long serialVersionUID = -6307207369191562275L;
}
