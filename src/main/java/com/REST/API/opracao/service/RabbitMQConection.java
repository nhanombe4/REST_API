package com.REST.API.opracao.service;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class RabbitMQConection {
	private static final String NOME_EXCHANGE = "amq.direct";
	private static final String resultado = "resultado";

	private AmqpAdmin amqpAdmin;

	public RabbitMQConection(AmqpAdmin amqpAdmin) {

		this.amqpAdmin = amqpAdmin;
	}

	private Queue fila(String nomefila) {

		return new Queue(nomefila, true, false, false);

	}

	private DirectExchange trocaDirect() {

		return new DirectExchange(NOME_EXCHANGE);
	}

	private Binding relacionamento(Queue fila, DirectExchange troca) {

		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}

	@PostConstruct
	private void adicionar() {

		Queue result = this.fila(resultado);

		DirectExchange troca = this.trocaDirect();
		Binding relacina = this.relacionamento(result, troca);

		this.amqpAdmin.declareQueue(result);

		this.amqpAdmin.declareExchange(troca);

		this.amqpAdmin.declareBinding(relacina);
	}

	@RabbitListener(queues = "resultado")
	private void consumidor(Resultado resultado) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();

		// configure Object mapper for pretty print
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		// writing to console, can write to any output stream such as file
		String json = objectMapper.writeValueAsString(resultado);

		System.out.println(json);

	}

}
