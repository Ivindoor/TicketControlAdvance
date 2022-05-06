package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.State_config;

public interface State_configDao {

	/*
	 * 
	 */
	void saveState_config(State_config state_config);

	List<State_config> findAllState_config();

	void deleteState_configById_State(Long id_State);

	State_config findState_configById_State(Long id_State);

	void updateState_config(State_config state_config);

	State_config findState_configByState_config(String state_config);//pendiente

	State_config test();

}
