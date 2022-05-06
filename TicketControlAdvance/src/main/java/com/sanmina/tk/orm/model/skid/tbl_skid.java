package com.sanmina.tk.orm.model.skid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class tbl_skid {
	
	@Getter
	@Setter
	private String serial;
	
	
	@Getter
	@Setter
	private String partNumber;
	
	@Getter
	@Setter
	private String revision;
	
	@Getter
	@Setter
	private String po;
	
}
