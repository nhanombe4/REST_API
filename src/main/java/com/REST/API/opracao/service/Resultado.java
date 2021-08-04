package com.REST.API.opracao.service;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Resultado implements Serializable {

	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "result")
	private long result;

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}

	

	

	
	
}
