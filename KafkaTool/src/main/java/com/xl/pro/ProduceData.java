package com.xl.pro;

import com.xl.client.KafkaOptions;
import com.xl.client.KafkaProducerClient;
import com.xl.message.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProduceData {

	public static void main(String[] args){
		KafkaProducerClient kafkaProClient = KafkaProducerClient.getInstance();
		kafkaProClient.init(KafkaOptions.produceConfig());

		KafkaProducer<Long, Event> kafkaProducer = kafkaProClient.create();

		long i = 0;

		while (true){
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			i++;

			Event event = new Event();
			event.setTimestamp(i);
			event.setChannelType("google");
			event.setHttpMethod("post");
			event.setRequestHeaders("RequestHeaders");
			event.setUri("url");
			event.setSnapshotId(i);
			event.setChannelAction("click");
			event.setResponseHeaders("ResponseHeaders");

			kafkaProducer.send(new ProducerRecord<>("test", System.currentTimeMillis(), event));
		}
	}

}
