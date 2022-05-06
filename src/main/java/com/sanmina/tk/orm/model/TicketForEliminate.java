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
public class TicketForEliminate {

	private String usrName;

	private String department;

	@Id
	private String ticketOrSerial;

	private Integer enabled;
}
