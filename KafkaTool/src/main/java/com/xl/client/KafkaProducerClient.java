package com.xl.client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public class KafkaProducerClient {

  private static KafkaProducerClient INSTANCE = new KafkaProducerClient();
  private static Properties pro;

  private Producer producer;

  private KafkaProducerClient() {
  }

  public void init(Properties pro) {
    this.pro = pro;
    producer = new KafkaProducer(pro);
  }

  public Producer create(){

    return producer;
  }



}
