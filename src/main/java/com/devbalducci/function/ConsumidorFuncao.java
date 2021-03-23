package com.devbalducci.function;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumidorFuncao<T> {

	void consome(ConsumerRecord<String, T> record);
}
