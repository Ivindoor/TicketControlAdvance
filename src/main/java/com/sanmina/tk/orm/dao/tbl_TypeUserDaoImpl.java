package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_TypeUser;

@Repository("tbl_TypeUserDao")
@Transactional("txTicket")
public class tbl_TypeUserDaoImpl extends AbstractDAO{

	public void saveTbl_TypeUser(tbl_TypeUser tbl_typeuser) {
		persistTicket(tbl_typeuser);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_TypeUser> findAllTbl_TypeUser() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_TypeUser.class);
		return (List<tbl_TypeUser>) criteria.list();
	}

	public void deleteTbl_TypeUserByIdTypeUser(Long idTypeUser) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_TypeUser where idTypeUser = :idTypeUser");
		query.setLong("idTypeUser", idTypeUser);
		query.executeUpdate();
	}

	public tbl_TypeUser findTbl_TypeUserByIdTypeUser(Long idTypeUser) {
		System.out.println("??? "+idTypeUser);
		Criteria criteria = getSessionTicket().createCriteria(tbl_TypeUser.class);
		criteria.add(Restrictions.eq("idTypeUser", idTypeUser));//
		return (tbl_TypeUser) criteria.uniqueResult();
	}

	public void updateTbl_TypeUser(tbl_TypeUser tbl_typeuser) {
		getSessionTicket().update(tbl_typeuser);
	}

	public tbl_TypeUser findTbl_TypeUserByTbl_TypeUser(String tbl_typeuser) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_TypeUser.class);
		criteria.add(Restrictions.eq("idTypeUser", tbl_typeuser));
		return (tbl_TypeUser) criteria.uniqueResult();
	}

	public tbl_TypeUser test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_TypeUser WHERE idTypeUser like '%1%'")
				.addEntity(tbl_TypeUser.class);
		return (tbl_TypeUser) query.uniqueResult();
	}

}
