package com.xl;

import kafka.metrics.KafkaMetricsReporter;
import kafka.server.KafkaConfig;
import kafka.server.KafkaServer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.utils.Time;
import scala.Option;
import scala.collection.JavaConversions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class MiniKafkaCluster {

	private static final int DEFAULT_BROKERS = 2;
	public MiniZookeeperCluster zookeeper;
	public int brokerNum;
	public boolean managedZk;
	private List<KafkaConfig> brokerConfigs;
	private List<KafkaServer> brokerServers;
	private int DEFAULT_PARTITIONS = 2;
	private final String dir;

	public MiniKafkaCluster() {
		this(DEFAULT_BROKERS, null);
	}

	public <K, V> Producer<K, V> createProducer(Class<?> keySerializerClass,
																							Class<?> valueSerializerClass) {
		Properties properties = getProducerProperties(keySerializerClass, valueSerializerClass);
		Producer<K, V> producer = new KafkaProducer<>(properties);

		return producer;
	}

	public <K, V> Consumer<K, V> createConsumer(Class<?> keyDeserializerClass,
																							Class<?> valueDeserializerClass) {
		return createConsumer(keyDeserializerClass, valueDeserializerClass, "testgroupID");
	}

	public <K, V> Consumer<K, V> createConsumer(Class<?> keyDeserializerClass,
																							Class<?> valueDeserializerClass,
																							String groupID) {
		Properties properties = getConsumerProperties(
			keyDeserializerClass, valueDeserializerClass, groupID);
		Consumer<K, V> consumer = new KafkaConsumer<>(properties);

		return consumer;
	}

	public Properties getProducerProperties(Class<?> keySerializerClass,
																					Class<?> valueSerializerClass) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass.getName());
		props.put("request.timeout.ms", "10000");
		props.put("retry.backoff.ms", "500");
		props.put("delivery.timeout.ms", 30000);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("acks", "all");
		props.put("retries", 0);
		return props;
	}

	public Properties getConsumerProperties(Class<?> keyDeserializerClass,
																					Class<?> valueDeserializerClass) {
		return getConsumerProperties(keyDeserializerClass,
			valueDeserializerClass, "testgroupID");
	}

	public Properties getConsumerProperties(Class<?> keyDeserializerClass,
																					Class<?> valueDeserializerClass,
																					String groupID) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
			keyDeserializerClass.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
			valueDeserializerClass.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}

	public MiniKafkaCluster(int brokerNum, MiniZookeeperCluster zookeeper) {
		assert brokerNum > 0;

		this.brokerNum = brokerNum;
		if (zookeeper == null) {
			this.managedZk = true;
			this.zookeeper = new MiniZookeeperCluster();
		} else {
			this.managedZk = false;
			this.zookeeper = zookeeper;
		}

		try {
			dir = "";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void startup() {
		zookeeper.start();

		brokerConfigs = getBrokerConfig(brokerNum);
		brokerServers = new ArrayList<>(brokerConfigs.size());
		for (KafkaConfig config : brokerConfigs) {
			brokerServers.add(startBroker(config));
		}
	}

	private List<KafkaConfig> getBrokerConfig(int brokers) {
		assert brokers >= 1;
		assert dir != null;
		assert zookeeper != null;

		try {
			Properties properties = new Properties();
			properties.put("zookeeper.connect", zookeeper.getConnectionString());
			properties.put("host.name", "127.0.0.1");
			properties.put("log.dir", "");
			properties.put("log.flush.interval.messages", "1");
			properties.put("num.partitions", String.valueOf(DEFAULT_PARTITIONS));
			properties.put("default.replication.factor", String.valueOf(brokers));
			properties.put("auto.create.topics.enable", "true");
			properties.put("offsets.topic.replication.factor", "1");

			List<KafkaConfig> configs = new ArrayList<>(brokers);
			for (int i = 0; i < brokers; i++) {
				Properties props = new Properties();
				props.putAll(properties);
				props.put("broker.id", String.valueOf(i + 1));
				props.put("port", "1000001");

				configs.add(new KafkaConfig(props));
			}

			return configs;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private KafkaServer startBroker(KafkaConfig config) {
		KafkaServer server = new KafkaServer(config, new SystemTime()
			, Option.<String>empty(),
			JavaConversions.asScalaBuffer(Collections.<KafkaMetricsReporter>emptyList()));
		server.startup();

		return server;
	}

	private static class SystemTime implements Time {

		public long milliseconds() {
			return System.currentTimeMillis();
		}

		public long hiResClockMs() {
			return System.currentTimeMillis();
		}

		public long nanoseconds() {
			return System.nanoTime();
		}

		public void sleep(long ms) {
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				// Ignore
			}
		}

	}

}
