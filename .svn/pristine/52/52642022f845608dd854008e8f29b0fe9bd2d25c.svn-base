package com.sanmina.tk.orm.dao.skid;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.AbstractDAO;
import com.sanmina.tk.orm.model.skid.Proyecto;

@Repository("ProyectoDao")
@Transactional("txsManagerSkid")

public class ProyectoDaoImpl extends AbstractDAO implements ProyectoDao  {

	@Override
	public void saveProyecto(Proyecto Proyecto) {
		persistSkid(Proyecto);		
	}

	@Override
	public void updateProyecto(Proyecto Proyecto) {
		getSessionSkid().update(Proyecto);
		
	}

	@Override
	public void deleteProyecto(Proyecto Proyecto) {
		getSessionSkid().delete(Proyecto);
		
	}

	@Override
	public Proyecto findProyecto(Proyecto Proyecto) {
		// TODO Auto-generated method stub
		return null;
	}

}
