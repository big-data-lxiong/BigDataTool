package com.xl.client;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KakfaConsumerClient <K, V extends GenericRecord>{

  private static KakfaConsumerClient INSTANCE = new KakfaConsumerClient();
  private static Properties pro;

  private Consumer<K, V> consumer;

  private KakfaConsumerClient() {
  }

  public void init(Properties pro) {
    this.pro = pro;
    consumer = new KafkaConsumer<K, V>(pro);
  }

  public Consumer<K, V> create(){
    try {
      consumer.subscribe(Arrays.asList((String)pro.get("topic")));
    }catch (Exception e){

    }
    return consumer;
  }

}
