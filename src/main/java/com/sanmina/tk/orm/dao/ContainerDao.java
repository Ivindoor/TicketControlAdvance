package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Container;
import com.sanmina.tk.orm.model.Ticket;

public interface ContainerDao {
	
	void saveContainer(Container container);

	List<Container> findAllContainers();

	void deleteContainer(Container container);

	Container findContainerByIdContainer(Long idContainer);

	Container findContainerByIdTicket(Long idTicket);
	
	List<Container> findContainersByTicket(Ticket ticket);
	
	List<Container> findContainerByLike(String container);
	
	void updateContainer(Container container);

	Container findContainerByContainer(String container);

	Container test();

	
	
	
}
