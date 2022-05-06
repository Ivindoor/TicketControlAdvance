package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_UserLogin;

@Repository("tbl_UserLoginDao")
@Transactional("txsManagerTicket")
public class tbl_UserLoginDaoImpl extends AbstractDAO implements tbl_UserLoginDao{
	/*
	 * 
	 */
	public void saveTbl_UserLogin(tbl_UserLogin tbl_userlogin) {
		persistTicket(tbl_userlogin);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_UserLogin> findAllTbl_UserLogin() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_UserLogin.class);
		return (List<tbl_UserLogin>) criteria.list();
	}

	public void deleteTbl_UserLoginByIdUser(Long idUser) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_UserLogin where idUser = :idUser");
		query.setLong("idUser", idUser);
		query.executeUpdate();
	}

	
	//tbl_UserLogin
	public tbl_UserLogin findTbl_UserLoginByIdUser(Long idUser) {
		System.out.println("??? "+idUser);
		Criteria criteria = getSessionTicket().createCriteria(tbl_UserLogin.class);
		criteria.add(Restrictions.eq("idUser", idUser));//
		return (tbl_UserLogin) criteria.uniqueResult();
	}

	public void updateTbl_UserLogin(tbl_UserLogin tbl_userlogin) {
		getSessionTicket().update(tbl_userlogin);
	}

	public tbl_UserLogin findTbl_UserLoginByTbl_UserLogin(String tbl_UserLogin) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_UserLogin.class);
		criteria.add(Restrictions.eq("idUser", tbl_UserLogin));
		return (tbl_UserLogin) criteria.uniqueResult();
	}

	public tbl_UserLogin test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_UserLogin WHERE idUser like '%1%'")
				.addEntity(tbl_UserLogin.class);
		return (tbl_UserLogin) query.uniqueResult();
	}

	


	
	
}
