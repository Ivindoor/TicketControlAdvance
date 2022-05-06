package com.sanmina.tk.orm.dao.skid;

import com.sanmina.tk.orm.model.skid.Serial;

public interface SerialDao {
	
	void saveSerial(Serial Serial);

	void updateSerial(Serial Serial);
	
	void  deleteSerial(Serial Serial);
	
	Serial findSerialBySerial(String serial);
	
}
