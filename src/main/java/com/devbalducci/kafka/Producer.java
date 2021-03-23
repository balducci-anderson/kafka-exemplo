package com.devbalducci.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.devbalducci.serializer.GsonSerializer;

public class Producer<T> {

	private final KafkaProducer<String, T> producer;
	private final String topico;
	
	public Producer(String topico){
		producer = new KafkaProducer<String, T>(properties());
		this.topico = topico;
	}
	
	@SuppressWarnings("unused")
	public void run(T object) throws InterruptedException, ExecutionException {
		ProducerRecord<String, T> record = new ProducerRecord<String, T>(topico, object);
		producer.send(record, (data, ex) -> {
			if(ex != null)
				System.out.println("--- Compra de Ingresso Enviada");
		}).get();
	}
	
	private Properties properties() {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
		return properties;
	}
}
