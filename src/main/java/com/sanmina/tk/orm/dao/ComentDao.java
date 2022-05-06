package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Coment;


public interface ComentDao{
	
	
	void saveComent(Coment coment);

	List<Coment> findAllComents();

	void deleteComentByIdComent(Long id_coment);

	Coment findComentByIdComent(Long id_coment);

	void updateComent(Coment coment);

	Coment findComentByComent(String coment);

	Coment test();

	
	
	
	
	
	
}
