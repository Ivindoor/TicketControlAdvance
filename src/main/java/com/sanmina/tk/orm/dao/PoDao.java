package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Po;

public interface PoDao {

	void savePo(Po po);

	void  deletePoByPo(Po po);

	void updatePo(Po po);
	
	List<Po> findAllPo();
	
	List<Po> findPriorityPo();
	
	Po findPoByIdPo(int idPo);
	
	Po findPoByPo(String po);
	
	Po findPoByIdShippingInstruction(int idShippingInstruction);
}
