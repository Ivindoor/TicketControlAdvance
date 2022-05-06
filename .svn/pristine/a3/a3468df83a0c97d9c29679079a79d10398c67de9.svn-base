package com.sanmina.tk.orm.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.FileLog;

@Repository("fileLogDao")
@Transactional("txTicket")
public class FileLogDaoImpl extends AbstractDAO implements FileLogDao {

	@Override
	public void saveFileLog(FileLog fileLog) {
		try {
			getSessionTicket().persist(fileLog);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFileLog(FileLog fileLog) {
		try {
			getSessionTicket().delete(fileLog);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void updateFileLog(FileLog fileLog) {
		try {
			getSessionTicket().update(fileLog);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
