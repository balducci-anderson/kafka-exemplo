package com.devbalducci.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.devbalducci.kafka.Consumer;
import com.devbalducci.modelo.Ingresso;

public class SalvaIngresso {

	public static void main(String[] args) {
		
		SalvaIngresso salvaIngresso = new SalvaIngresso();
		Consumer<Ingresso> consumer = new Consumer<>(Ingresso.class, 
				SalvaIngresso.class.getSimpleName(), salvaIngresso::parse, "NOVO_INGRESSO");
		consumer.run();
	}
	
	public void parse(ConsumerRecord<String, Ingresso> record) {
		System.out.println("---- Ingresso recebido");
		System.out.println("Id: " + record.value().getId());
		System.out.println("Evento: " + record.value().getEvento());
		System.out.println("Quantidade: " + record.value().getQuantidade());
	}
}
