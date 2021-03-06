package com.sanmina.tk.orm.dao;

/*
 * 
 */
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Users;
/*
**Created By Jose Antonio Fonseca
**jose.fonseca@sanmina.com
**IT FIELD Plant 2
**Sanmina SCI CORP. 2016
*/
@Repository("userDao")
@Transactional("txsManagerTicket")
public class UserDaoImpl extends AbstractDAO implements UserDao {
	/*
	 * 
	 */
	public void saveUser(Users user) {
		persistTicket(user);
	}

	@SuppressWarnings("unchecked")
	public List<Users> findAllUsers() {
		Criteria criteria = getSessionTicket().createCriteria(Users.class);
		return (List<Users>) criteria.list();
	}

	public void deleteUserByUserName(String usName) {
		Query query = getSessionTicket().createSQLQuery("delete from Users where universalLogin = :universalLogin");
		query.setString("universalLogin", usName);
		query.executeUpdate();
	}

	public Users findUserByUserName(String usName) {
		System.out.println("??? "+usName);
		Criteria criteria = getSessionTicket().createCriteria(Users.class);
		criteria.add(Restrictions.eq("universalLogin", usName));
		return (Users) criteria.uniqueResult();
	}

	public void updateUser(Users RdlUsers) {
		getSessionTicket().update(RdlUsers);
	}

	public Users findUserByUserLogin(String usLogin) {
		Criteria criteria = getSessionTicket().createCriteria(Users.class);
		criteria.add(Restrictions.eq("universalLogin", usLogin));
		return (Users) criteria.uniqueResult();
	}

	public Users test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_UserLogin WHERE Nombre like '%ivan%'")
				.addEntity(Users.class);
		return (Users) query.uniqueResult();
	}

}
