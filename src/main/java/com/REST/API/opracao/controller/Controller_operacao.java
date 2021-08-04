package com.REST.API.opracao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.REST.API.opracao.service.Operacao;
import com.fasterxml.jackson.core.JsonProcessingException;



@RestController

public class Controller_operacao {
  
	
	
	
	@Autowired
	private Operacao op;
	
	
	@GetMapping("/sum")
	public String soma(@RequestParam   int a, @RequestParam int b) throws JsonProcessingException {
		
	

		return op.soma(a,b);
	}
	
	@GetMapping("/sub")
	public String subtracao(@RequestParam   int a, @RequestParam int b) throws JsonProcessingException {
		
		
		return op.subtracao(a,b);
	}
	
	@GetMapping("/mult")
	public String multiplicacao(@RequestParam   int a, @RequestParam int b) throws JsonProcessingException {
		
		
		return op.multiplicacao(a,b);
	}
	
	@GetMapping("/div")
	public String divisao(@RequestParam   int a, @RequestParam int b) throws JsonProcessingException {
		
		
		return op.divisao(a,b);
	}
	
	
}
