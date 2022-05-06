package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.EchoStarValid;

@Repository("tbl_EchoStarValid_ConfigDao")
@Transactional("txTicket")
public class EchoStarValidDaoImpl extends AbstractDAO implements EchoStarValidDao {

	public void saveByEchoStarValid(EchoStarValid echostarvalid) {
		Transaction tx = null;
		try {
			tx = getSessionTicket().getTransaction();
			getSessionTicket().persist(echostarvalid);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}

	public void deleteByEchoStarValid(EchoStarValid echostarvalid) {
		Transaction tx = null;
		try {
			tx = getSessionTicket().getTransaction();
			getSessionTicket().delete(echostarvalid);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}

	public void updateByEchoStarValid(EchoStarValid echostarvalid) {
		Transaction tx = null;
		try {
			tx = getSessionTicket().getTransaction();
			getSessionTicket().update(echostarvalid);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<EchoStarValid> findAllEchoStarValid() {
		Criteria criteria = getSessionTicket().createCriteria(EchoStarValid.class);
		return (List<EchoStarValid>) criteria.list();
	}

	public EchoStarValid findEchoStarValidByPartNumber(String partNumber) {
		Criteria criteria = getSessionTicket().createCriteria(EchoStarValid.class);
		criteria.add(Restrictions.eq("partNumber", partNumber));//
		return (EchoStarValid) criteria.uniqueResult();
	}

}
