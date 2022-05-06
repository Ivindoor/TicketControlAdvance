package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.tblInstruction;

public interface tblInstructionDao {

	List<tblInstruction> findAllInstruction(String project);
}
