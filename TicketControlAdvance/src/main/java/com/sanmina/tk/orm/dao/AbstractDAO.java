package com.sanmina.tk.orm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractDAO {
	
	@Autowired
	@Qualifier("SQLTicket")
	private SessionFactory sessionFactoryTicket;
	
	@Autowired
	@Qualifier("SQLSkid")
	private SessionFactory sessionFactorySkid;

	protected Session getSessionTicket() {
		return sessionFactoryTicket.getCurrentSession();
	}

	public void persistTicket(Object entity) {
		getSessionTicket().persist(entity);
	}

	public void deleteTicket(Object entity) {
		getSessionTicket().delete(entity);
	}

	public void updateTicket(Object entity) {
		getSessionTicket().update(entity);
	}
	
	protected Session getSessionSkid() {
		return sessionFactorySkid.getCurrentSession();
	}

	public void persistSkid(Object entity) {
		getSessionSkid().persist(entity);
	}

	public void deleteSkid(Object entity) {
		getSessionSkid().delete(entity);
	}

	public void updateSkid(Object entity) {
		getSessionSkid().update(entity);
	}

}
