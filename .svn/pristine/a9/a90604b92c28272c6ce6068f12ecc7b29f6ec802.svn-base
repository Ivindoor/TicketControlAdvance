package com.sanmina.tk.orm.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.ContainerBox;



@Repository("container_BoxDao")
@Transactional("txsManagerTicket")
public class Container_BoxDaoImpl extends AbstractDAO{
	
	/*
	 * 
	 */
	public void saveContainer_Box(ContainerBox containerBox) {
		persistTicket(containerBox);
	}

	@SuppressWarnings("unchecked")
	public List<ContainerBox> findAllBoxes() {
		Criteria criteria = getSessionTicket().createCriteria(ContainerBox.class);
		return (List<ContainerBox>) criteria.list();
	}

	public void deleteContainer_BoxById(Integer id) {
		Query query = getSessionTicket().createSQLQuery("delete from Container_Box where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public ContainerBox findContainer_BoxById(Integer id) {
		System.out.println("??? "+id);
		Criteria criteria = getSessionTicket().createCriteria(ContainerBox.class);
		criteria.add(Restrictions.eq("id", id));
		return (ContainerBox) criteria.uniqueResult();
	}

	public void updateContainerBox(ContainerBox containerBox) {
		getSessionTicket().update(containerBox);
	}

	public ContainerBox findContainer_BoxById_Box(String container_box) {
		Criteria criteria = getSessionTicket().createCriteria(ContainerBox.class);
		criteria.add(Restrictions.eq("id", container_box));
		return (ContainerBox) criteria.uniqueResult();
	}

	public ContainerBox test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Container_box  WHERE  id like '%1%'")
				.addEntity(ContainerBox.class);
		return (ContainerBox) query.uniqueResult();
	}

	
	
	
	
	
}