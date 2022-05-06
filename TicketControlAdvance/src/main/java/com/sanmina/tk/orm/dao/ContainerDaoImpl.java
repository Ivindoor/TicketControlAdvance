package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Container;
import com.sanmina.tk.orm.model.Ticket;

@Repository("containerDao")
@Transactional("txsManagerTicket")
public class ContainerDaoImpl extends AbstractDAO implements ContainerDao {
	
	public void saveContainer(Container container) {
		getSessionTicket().save(container);
	}

	@SuppressWarnings("unchecked")
	public List<Container> findAllContainers() {
		Criteria criteria = getSessionTicket().createCriteria(Container.class);
		return (List<Container>) criteria.list();
	}

	public void deleteContainerByIdContainer(Long idContainer) {
		getSessionTicket().delete(idContainer);
	}

	public Container findContainerByIdContainer(Long IdContainer) {
		Criteria criteria = getSessionTicket().createCriteria(Container.class);
		criteria.add(Restrictions.eq("idContainers", IdContainer));
		return (Container) criteria.uniqueResult();
	}

	public void updateContainer(Container container) {
		getSessionTicket().update(container);
	}

	public Container findContainerByContainer(String container) {
		Criteria criteria = getSessionTicket().createCriteria(Container.class);
		criteria.add(Restrictions.eq("container", container));
		return (Container) criteria.uniqueResult();
	}

	public Container test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM Containers WHERE id_containers like '%1%'")
				.addEntity(Container.class);
		return (Container) query.uniqueResult();
	}

	public Container findContainerByIdTicket(Long idTicket) {
		Criteria criteria = getSessionTicket().createCriteria(Container.class);
		criteria.add(Restrictions.eq("idTicket", idTicket));
		return (Container) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Container> findContainersByTicket(Ticket ticket) {
		Criteria criteria = getSessionTicket().createCriteria(Container.class);
		criteria.add(Restrictions.eq("idTicket", ticket.getIdTicket()));
		return (List<Container>) criteria.list();
	}
}
