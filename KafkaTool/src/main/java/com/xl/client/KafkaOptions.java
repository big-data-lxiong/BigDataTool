package com.xl.client;

import java.util.Properties;

public class KafkaOptions {

	public static Properties produceConfig(){
		Properties prop = new Properties();
		prop.setProperty("zookeeper.connect", "localhost:2181");
		prop.setProperty("bootstrap.servers", "localhost:9092");
		prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
		prop.setProperty("value.serializer", "com.xl.message.EventSerializer");

		return prop;
	}

	public static Properties consumerConfig(){
		Properties prop = new Properties();
		prop.setProperty("", "");
		prop.setProperty("", "");
		prop.setProperty("", "");
		prop.setProperty("", "");

		return prop;
	}

}
