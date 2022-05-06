package com.sanmina.tk.orm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractDAO {
	
	@Autowired
	@Qualifier("sessionTicket")
	private SessionFactory sessionFactoryTicket;
	
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
	
	@Autowired
	@Qualifier("sessionSkid")
	private SessionFactory sessionFactorySkid;
	
	protected Session getSessionSkid() {
		return sessionFactorySkid.getCurrentSession();
	}
}
