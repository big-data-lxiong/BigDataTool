import com.xl.message.{Event, EventDeserializer}
import org.apache.avro.generic.GenericRecord
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.parquet.avro.AvroParquetWriter
import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaToStreaming extends App {

  @transient lazy val hadoopConf = {
    new Configuration()
  }

  override def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("KafkaToStreaming")
      .setMaster("local[2]")

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[LongDeserializer],
      "value.deserializer" -> classOf[EventDeserializer],
      "group.id" -> "group_test",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (true: java.lang.Boolean)
    )

    val topics = Array("test")

    //一分钟执行一次
    val ssc = new StreamingContext(conf, Seconds(60))
    val kafkaStreaming = KafkaUtils.createDirectStream(ssc, PreferConsistent, Subscribe[java.lang.Long, Event](topics, kafkaParams))

//        val lines = kafkaStreaming.map(record => record.value).saveAsTextFiles( "hdfs://localhost:9000/apps/sparkstreaming/data")

    kafkaStreaming.foreachRDD(rdd => {
      rdd.foreachPartition(ite => {
        val writer  = AvroParquetWriter
          .builder[GenericRecord](new Path("hdfs://localhost:9000/apps/sparkstreaming/data-" + System.currentTimeMillis() + ".parquet"))
          .withSchema(Event.getClassSchema())
          .withConf(hadoopConf)
          .withCompressionCodec(CompressionCodecName.UNCOMPRESSED)
          .build()

        while (ite.hasNext) {
          val event = ite.next().value()
          writer.write(event)
        }
        writer.close()

      })
    })

    ssc.start()

    ssc.awaitTermination()
  }
}
