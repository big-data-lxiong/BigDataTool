package com.xl.client;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class KafkaProducerClient <K, V extends GenericRecord>  {

  private static KafkaProducerClient INSTANCE = new KafkaProducerClient();
  private static Properties pro;

  private KafkaProducer producer;

  private KafkaProducerClient() {
  }

  public void init(Properties pro) {
    this.pro = pro;
    producer = new KafkaProducer(pro);
  }

  public static KafkaProducerClient getInstance(){
    return INSTANCE;
  }

  public KafkaProducer<K, V> create(){

    return producer;
  }



}
