package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Cree_config;

public interface Cree_configDao {
	/*
	 * 
	 */
	void saveCree_config(Cree_config cree_config);

	List<Cree_config> findAllCree_config();

	void deleteCree_configByIdConfig(Long idConfig);

	Cree_config findCree_configByIdConfig(Long idConfig);

	void updateCree_config(Cree_config cree_config);

	Cree_config findCree_configByCree_config(String cree_config);

	Cree_config test();

}
