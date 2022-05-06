package com.sanmina.tk.orm.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ContainerSerial {

	@Id
	private Integer ID;
	private String Serial_Number;
	private String process_name;
	private String Action;
}
