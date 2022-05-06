package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.tbl_BoxesDeleted;

@Repository("tbl_BoxesDeletedDao")
@Transactional("txTicket")
public class tbl_BoxesDeletedDaoImpl extends AbstractDAO implements tbl_BoxesDeletedDao {

	public void saveTbl_BoxesDeleted(tbl_BoxesDeleted tbl_boxesdeleted) {
		persistTicket(tbl_boxesdeleted);
	}

	@SuppressWarnings("unchecked")
	public List<tbl_BoxesDeleted> findAllTbl_BoxesDeleted() {
		Criteria criteria = getSessionTicket().createCriteria(tbl_BoxesDeleted.class);
		return (List<tbl_BoxesDeleted>) criteria.list();
	}

	public void deleteTbl_BoxesDeletedById_Box(Long id_box) {
		Query query = getSessionTicket().createSQLQuery("delete from tbl_BoxesDeleted where id_Box = :id_Box");
		query.setLong("id_Box", id_box);
		query.executeUpdate();
	}

	public tbl_BoxesDeleted findtbl_BoxesDeletedById_Box(Long id_box) {
		System.out.println("??? "+id_box);
		Criteria criteria = getSessionTicket().createCriteria(tbl_BoxesDeleted.class);
		criteria.add(Restrictions.eq("id_Box", id_box));//
		return (tbl_BoxesDeleted) criteria.uniqueResult();
	}

	public void updatetbl_BoxesDelete(tbl_BoxesDeleted tbl_boxesdeleted) {
		getSessionTicket().update(tbl_boxesdeleted);
	}

	public tbl_BoxesDeleted findtbl_BoxesDeletedBytbl_BoxesDeleted(String tbl_boxesdeleted) {
		Criteria criteria = getSessionTicket().createCriteria(tbl_BoxesDeleted.class);
		criteria.add(Restrictions.eq("id_Box", tbl_boxesdeleted));
		return (tbl_BoxesDeleted) criteria.uniqueResult();
	}

	public tbl_BoxesDeleted test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM tbl_BoxesDeleted WHERE id_Box like '%1%'")
				.addEntity(tbl_BoxesDeleted.class);
		return (tbl_BoxesDeleted) query.uniqueResult();
	}

}
