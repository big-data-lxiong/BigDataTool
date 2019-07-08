package com.xl

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.{Seconds, StreamingContext}

class KafkaToStreaming {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("KafkaToStreaming")
      .setMaster("local[2]")

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092,anotherhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("topicA", "topicB")

    //一分钟执行一次
    val ssc=new StreamingContext(conf,Seconds(60))
    val kafkaStreaming=KafkaUtils.createDirectStream(ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))

    kafkaStreaming.map(record => (record.key, record.value))


    ssc.start()

    ssc.awaitTermination()
  }
}
