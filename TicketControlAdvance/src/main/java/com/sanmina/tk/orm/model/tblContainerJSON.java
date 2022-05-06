package com.sanmina.tk.orm.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.NumberDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import lombok.Getter;
import lombok.Setter;

public class tblContainerJSON {

	@JsonDeserialize(using = StringDeserializer.class)
	@Getter @Setter private String Contenedor;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@Getter @Setter private String NumeroParte;
	
	@JsonDeserialize(using = NumberDeserializer.class)
	@Getter @Setter private Integer Cantidad;
	
	public tblContainerJSON(){}
}
