package com.xl;

import kafka.metrics.KafkaMetricsReporter;
import kafka.server.KafkaConfig;
import kafka.server.KafkaServer;
import org.apache.kafka.common.utils.Time;
import scala.Option;
import scala.collection.JavaConversions;

import java.util.Collections;
import java.util.Properties;

public class MiniKafkaCluster {

  public MiniZookeeperCluster zookeeper;

  public MiniKafkaCluster(){
    this.zookeeper = new MiniZookeeperCluster();
  }

  public void startup(){
    zookeeper.start();

    startBroker();
  }

  private void startBroker() {
    KafkaServer server = new KafkaServer(new KafkaConfig(new Properties()), new SystemTime()
            , Option.<String>empty(),
            JavaConversions.asScalaBuffer(Collections.<KafkaMetricsReporter>emptyList()));
    server.startup();
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
