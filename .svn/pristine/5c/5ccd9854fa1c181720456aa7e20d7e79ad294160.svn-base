package com.sanmina.tk.orm.dao;

import java.util.List;

import com.sanmina.tk.orm.model.Po;
import com.sanmina.tk.orm.model.PoLine;

public interface PoLineDao {

	void savePoLine(PoLine poLine);
	
	void saveOrUpdatePoLine(PoLine poLine);
	
	void deletePoLine(PoLine poLine);
	
	void updatePoLine(PoLine poLine);
	
	List<PoLine> findAllPoLine();
	
	List<PoLine> findPoLinesByPo(Po po);
	
	PoLine findPoLineByPoLine(String poLine);
	
	PoLine findPoLineByIdPoLine(Integer idPoLine);
}
