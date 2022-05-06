package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.State_config;

@Repository("state_configDao")
@Transactional("txTicket")
public class State_configDaoImpl extends AbstractDAO implements State_configDao{

	public void saveState_config(State_config state_config) {
		persistTicket(state_config);
	}

	@SuppressWarnings("unchecked")
	public List<State_config> findAllState_config() {
		Criteria criteria = getSessionTicket().createCriteria(State_config.class);
		return (List<State_config>) criteria.list();
	}

	public void deleteState_configById_State(Long id_state) {
		Query query = getSessionTicket().createSQLQuery("delete from State_config where id_State = :id_State");
		query.setLong("id_State", id_state);
		query.executeUpdate();
	}

	public State_config findState_configById_State(Long id_State) {
		System.out.println("??? "+id_State);
		Criteria criteria = getSessionTicket().createCriteria(State_config.class);
		criteria.add(Restrictions.eq("id_State", id_State));//
		return (State_config) criteria.uniqueResult();
	}

	public void updateState_config(State_config state_config) {
		getSessionTicket().update(state_config);
	}

	public State_config findState_configByState_config(String state_config) {
		Criteria criteria = getSessionTicket().createCriteria(State_config.class);
		criteria.add(Restrictions.eq("id_State", state_config));
		return (State_config) criteria.uniqueResult();
	}

	public State_config test() {
		SQLQuery query = getSessionTicket().createSQLQuery("Select * FROM State_config WHERE id_State like '%1%'")
				.addEntity(State_config.class);
		return (State_config) query.uniqueResult();
	}

	

	


}
