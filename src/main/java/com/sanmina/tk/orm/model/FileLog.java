package com.sanmina.tk.orm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblFileLog", schema = "dbo", catalog = "SFDC_Ticket_Control")
@NoArgsConstructor
public class FileLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLog")
	@Getter
	@Setter
	private Integer idLog;
	
	@Column(name = "shippingInstruction")
	@Getter
	@Setter
	private String shippingInstruction;
	
	@Column(name = "po")
	@Getter
	@Setter
	private String po;
	
	@Column(name = "poLine")
	@Getter
	@Setter
	private String poLine;

	@Column(name = "partNumber")
	@Getter
	@Setter
	private String partNumber;
	
	@Column(name = "creationDate")
	@Getter
	@Setter
	private Date creationDate;
	
	@Column(name = "updateDate")
	@Getter
	@Setter
	private Date updateDate;
	
	@Column(name = "userName")
	@Getter
	@Setter
	private String userName;
}
