package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.ShippingInstruction;;

public interface ShippingInstructionDao {

	void saveShippingInstruction(ShippingInstruction instruction);

	void  deleteShippingInstructionByShippingInstruction(ShippingInstruction instruction);

	void updateShippingInstruction(ShippingInstruction instruction);
	
	List<ShippingInstruction> findAllShippingInstruction();
	
	ShippingInstruction findPriorityShippingInstruction();
	
	ShippingInstruction findInstruccionByIdShippingInstruction(int idShippingInstruction);
	
	ShippingInstruction findInstruccionByShippingInstruction(String shippingInstruction);
}
