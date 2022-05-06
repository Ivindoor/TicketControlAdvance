package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Project;

public interface ProjectDao {
	/*
	 * 
	 */
	void saveProject(Project project);

	List<Project> findAllProjects();

	void deleteProjectByIdProject(Long idProject);

	Project findProjectByIdProject(Long idProject);

	void updateProject(Project project);

	Project findProjectByProject(String Project);//pendiente

	Project test();

	
}

