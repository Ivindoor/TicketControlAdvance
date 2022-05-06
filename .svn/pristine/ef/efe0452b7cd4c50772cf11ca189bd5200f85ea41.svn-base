package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.ContainerSerial;

@Transactional("txTicket")
@Service("MesDao")
public class MesDaoImpl extends AbstractDAO implements MesDao {

	@SuppressWarnings("unchecked")
	public List<String> findSerialByContainer(String container) {
		SQLQuery query = getSessionTicket()
				.createSQLQuery("SELECT DISTINCT S.[Serial_Number]" + "FROM [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] Se "
						+ "INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] S "
						+ "ON Se.[Project_pk_id] = 229 AND Se.[Pk_Id] = S.[skid_serial_pk_id] AND  Se.[Serial_Id] = S.[Skid_Id] "
						+ "WHERE Se.Serial_Number = '" + container + "';");
		return (List<String>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ContainerSerial> findProcessNameByConteiner(String container) {
		SQLQuery query = getSessionTicket()
				.createSQLQuery("SELECT DISTINCT row_number() OVER (ORDER BY S.Serial_Number, L.[process_name], A.[Action] ) ID, S.Serial_Number, L.[process_name], A.[Action] "
						+ "FROM [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] Se "
						+ "INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] S "
						+ "ON Se.[Project_pk_id] = 229 AND Se.Serial_Number = '" + container
						+ "' AND Se.[Pk_Id] = S.[skid_serial_pk_id] AND  Se.[Serial_Id] = S.[Skid_Id] "
						+ "INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[Activity] A "
						+ "ON S.Serial_Id = A.Serial_Id AND S.Pk_Id = A.[Serial_PK_Id] "
						+ "INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[Location] L "
						+ "ON A.[Location_PK_Id] = L.[Pk_Id] AND L.[process_name] = 'ICT' "
						+ "ORDER BY S.Serial_Number")
				.addEntity(ContainerSerial.class);
		return (List<ContainerSerial>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ContainerSerial> findProcessByConteiner(String container) {
		SQLQuery query = getSessionTicket()
				.createSQLQuery("SELECT DISTINCT row_number() OVER (ORDER BY S.Serial_Number, L.[process_name], A.[Action] ) ID, S.Serial_Number, L.[process_name], A.[Action] "
						+ "FROM [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] Se "
						+ "INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] S "
						+ "ON Se.[Project_pk_id] = 229 AND Se.Serial_Number=:container AND Se.[Pk_Id] = S.[skid_serial_pk_id] AND  Se.[Serial_Id] = S.[Skid_Id] "
						+ "INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[Activity] A "
						+ "ON S.Serial_Id = A.Serial_Id AND S.Pk_Id = A.[Serial_PK_Id] "
						+ "INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[Location] L "
						+ "ON A.[Location_PK_Id] = L.[Pk_Id] AND L.[process_name] IN ('ICT','FVS','HMU') ")
					//	+ "ORDER BY S.Serial_Number")
				.addEntity(ContainerSerial.class);
		query.setParameter("container", container);
		return (List<ContainerSerial>) query.list();
	}

	@Override
	public String findPartNumberByContainer(String container) {
		SQLQuery query = getSessionTicket().createSQLQuery(
				"SELECT [Part_Number] FROM [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] S INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[part] P "
						+ "ON S.[part_pk_id] = P.Pk_Id AND [Serial_Number] = '" + container + "'");
		return (String) query.uniqueResult();
	}

	@Override
	public Integer finQtyByContainer(String container) {
		SQLQuery query = getSessionTicket()
				.createSQLQuery("SELECT [Qty] FROM [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] WHERE Serial_Number = '"
						+ container + "'");
		return (Integer) query.uniqueResult();
	}

	@Override
	public String finDescriptionByContainer(String container) {
		SQLQuery query = getSessionTicket().createSQLQuery(
				"SELECT [Description] FROM [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[part] P INNER JOIN [MESWEB_GDL1AMAEGW01].[MesR].[dbo].[serial] S ON P.Pk_Id = S.[part_pk_id] AND S.Serial_Number = '"
						+ container + "'");
		return (String) query.uniqueResult();
	}

}
