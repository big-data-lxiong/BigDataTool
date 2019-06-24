package com.xl.client;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

public class KafkaProducerClient<K, V extends GenericRecord> {

  private static KafkaProducerClient INSTANCE = new KafkaProducerClient();

  private Consumer<K, V> consumer;

  private KafkaProducerClient() {
  }

  public void init(Properties pro) {
    consumer = new KafkaConsumer<K, V>(pro);
  }

  public Consumer<K, V> create(){
    consumer.subscribe();

    return consumer;
  }



}
