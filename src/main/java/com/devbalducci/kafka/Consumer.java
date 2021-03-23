package com.devbalducci.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.devbalducci.function.ConsumidorFuncao;
import com.devbalducci.serializer.GsonDeserializer;

public class Consumer<T> {

	private final KafkaConsumer<String, T> consumer;
	private final ConsumidorFuncao<T> parse;
	
	public Consumer(Class<T> type, String groupId, ConsumidorFuncao<T> parse, String topico) {
		consumer = new KafkaConsumer<>(properties(type, groupId));
		this.parse = parse;
		consumer.subscribe(Collections.singletonList(topico));
	}
	
	public void run() {
		
		while(true) {
			ConsumerRecords<String, T> records = consumer.poll(Duration.ofMillis(100));
			
			for(ConsumerRecord<String, T> record : records) {
				parse.consome(record);
			}
		}
	}
	
	public Properties properties(Class<T> type, String groupId) {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
		properties.setProperty(GsonDeserializer.TYPE_CONFIG, type.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		return properties;
	}
}
