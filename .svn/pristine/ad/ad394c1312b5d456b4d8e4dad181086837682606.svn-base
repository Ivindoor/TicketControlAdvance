package com.sanmina.tk.orm.dao.skid;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.AbstractDAO;
import com.sanmina.tk.orm.model.skid.Container;

@Repository("ContainerDao")
@Transactional("txsManagerSkid")
public class ContainerDaoImpl extends AbstractDAO implements ContainerDao {

	@Override
	public void saveContainer(Container container) {
		persistSkid(container);
	}

	@Override
	public void updateContainer(Container container) {
		getSessionSkid().update(container);
	}

	@Override
	public void deleteContainer(Container container) {
		getSessionSkid().delete(container);
	}

	@Override
	public Container findContainerByContainer(Long container) {
		Criteria criteria = getSessionSkid().createCriteria(Container.class);
		criteria.add(Restrictions.eq("conte_id", container));
		return (Container) criteria.uniqueResult();
	}

}
