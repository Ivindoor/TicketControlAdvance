package com.sanmina.tk.orm.dao.skid;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.dao.AbstractDAO;
import com.sanmina.tk.orm.model.skid.ContainerSkid;

@Repository("containerSkidDao")
@Transactional("txSkid")
public class ContainerSkidDaoImpl extends AbstractDAO implements ContainerSkidDao{

	@Override
	public ContainerSkid findContainerByContainer(Long container) {
		// TODO Auto-generated method stub
		Criteria criteria = getSessionSkid().createCriteria(ContainerSkid.class);
		criteria.add(Restrictions.eq("idContainer", container));
		return (ContainerSkid) criteria.uniqueResult();
	}

}
