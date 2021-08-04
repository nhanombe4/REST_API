package com.REST.API.opracao.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class Operacao {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	// calculo de soma
	public String soma(int a, int b) {

		Resultado resul = new Resultado();

		resul.setResult((a + b));

		String send = null;
		try {
			send = sendMessege(resul);

			return send;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// calculo da subtração
	public String subtracao(int a, int b) throws JsonProcessingException {

		Resultado resul = new Resultado();

		resul.setResult((a - b));

		String send = sendMessege(resul);
		return send;

	}

	// calculo da multiplicação
	public String multiplicacao(int a, int b) throws JsonProcessingException {

		Resultado resul = new Resultado();

		resul.setResult((a * b));

		String send = sendMessege(resul);
		return send;

	}

	//calculo da subtração
	public String divisao(int a, int b) throws JsonProcessingException {

		Resultado resul = new Resultado();

		if (b == 0) {

		} else {
			resul.setResult((a / b));
		}

		String send = sendMessege(resul);
		return send;

	}

	// metodo de envio para consumidor via Rabbimq
	public String sendMessege(Resultado resul) throws JsonProcessingException {

		
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		String json = objectMapper.writeValueAsString(resul);

		this.rabbitTemplate.convertAndSend("resultado", resul);

		return json;
	}

}
