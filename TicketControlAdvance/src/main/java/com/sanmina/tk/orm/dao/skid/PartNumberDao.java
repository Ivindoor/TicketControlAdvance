package com.sanmina.tk.orm.dao.skid;
import com.sanmina.tk.orm.model.skid.PartNumber;

public interface PartNumberDao {
	
	void savePartNumber(PartNumber PartNumber);

	void updateSerial(PartNumber PartNumber);
	
	void  deleteSerial(PartNumber PartNumber);
	
	PartNumber findPartByPartPrint (String partPrint);
	
	PartNumber findPartByPart (String partNumber);

}
