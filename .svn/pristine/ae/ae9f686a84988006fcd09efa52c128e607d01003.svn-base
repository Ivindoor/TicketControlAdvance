package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Project;

@Repository("projectDao")
@Transactional("txsManagerTicket")
public class ProjectDaoImpl extends AbstractDAO implements ProjectDao{
	/*
	 * 
	 */
	public void saveProject(Project project) {
		persistTicket(project);
	}

	@SuppressWarnings("unchecked")
	public List<Project> findAllProjects() {
		Criteria criteria = getSessionTicket().createCriteria(Project.class);
		return (List<Project>) criteria.list();
	}

	public void deleteProjectByIdProject(Long idProject) {
		Query query = getSessionTicket().createSQLQuery("delete from Project where idBox = :idBox");
		query.setLong("idProject", idProject);
		query.executeUpdate();
	}

	public Project findProjectByIdProject(Long idProject) {
		System.out.println("??? "+idProject);
		Criteria criteria = getSessionTicket().createCriteria(Project.class);
		criteria.add(Restrictions.eq("idProject", idProject));//
		return (Project) criteria.uniqueResult();
	}

	public void updateProject(Project project) {
		getSessionTicket().update(project);
	}

	public Project findProjectByProject(String project) {
		Criteria criteria = getSessionTicket().createCriteria(Project.class);
		criteria.add(Restrictions.eq("projectName", project));
		return (Project) criteria.uniqueResult();
	}

	public Project test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Project WHERE idProject like '%1%'")
				.addEntity(Project.class);
		return (Project) query.uniqueResult();
	}

	

	

	



}
