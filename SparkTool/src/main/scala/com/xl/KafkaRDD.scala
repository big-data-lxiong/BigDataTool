package com.xl

import org.apache.kafka.clients.consumer._
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

abstract class KafkaRDD[K, V] (
                       @transient val sc: SparkContext,
                       val topic: String,
                       val kafkaProperties: java.util.Properties,
                       val maxConsumeSize: Long = 100000l
                     ) extends RDD[ConsumerRecord[K, V]](sc, Nil) {

//  @transient lazy val consumer = {
//    kafkaProperties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
//    new KafkaConsumer[K, V](kafkaProperties)
//  }
//
//  @transient lazy val untilOffsets = {
//    val kpartitions = new java.util.ArrayList[TopicPartition]()
//    val ite = consumer.partitionsFor(topic).iterator()
//
//    while(ite.hasNext){
//      kpartitions.add(new TopicPartition(topic, ite.next().partition()))
//    }
//
//    val endoffsets = consumer.endOffsets(kpartitions)
//
//    val untilOffsets = new java.util.HashMap[TopicPartition, Long]()
//    val endOffsetIter = endoffsets.entrySet().iterator()
//    while(endOffsetIter.hasNext){
//      val endoffset = endOffsetIter.next()
//      val tp = endoffset.getKey
//      val pos = consumer.position(tp)
//      val until = Math.min(endoffset.getValue, pos + maxConsumeSize)
//      if (until > pos) {
//        untilOffsets.put(endoffset.getKey, until)
//      }
//    }
//
//    consumer.unsubscribe()
//
//    untilOffsets
//  }
//
//  def commitOffsets() = {
//    consumer.commitSync(untilOffsets)
//  }
//
//  def close() = {
//    consumer.close()
//  }
//
//  override def compute(partition: Partition, context: TaskContext): Iterator[ConsumerRecord[K, V]] = {
//    val part = partition.asInstanceOf[KafkaRDDPartition]
//
//    // assign topic partitions to consumer
//    consumer.assign(java.util.Arrays.asList(part.tp))
//    context.addTaskCompletionListener(context => {
//      consumer.close()
//    })
//
//    new KafkaRDDIterator(part, consumer, context)
//  }
//
//  override protected def getPartitions: Array[Partition] = {
//    val paritions = new Array[Partition](untilOffsets.size())
//    val iterator = untilOffsets.entrySet().iterator()
//    var index = 0
//    while (iterator.hasNext) {
//      val entry = iterator.next()
//      partitions(index) = new KafkaRDDPartition(index, entry.getKey, entry.getValue())
//      index = index + 1
//    }
//    partitions
//  }
//
//  private class KafkaRDDIterator(
//                                  val part: KafkaRDDPartition,
//                                  val consumer: Consumer[K, V],
//                                  val context: TaskContext
//                                ) extends Iterator[ConsumerRecord[K, V]] {
//    val topicPartition = part.tp
//    var offset = consumer.position(topicPartition)
//
//    var nextRecord: ConsumerRecord[K, V] = null // cache the next record
//
//    var buffer: util.Iterator[ConsumerRecord[K, V]] = null
//
//    override def hasNext: Boolean = offset < part.untilOffset && getNext
//
//    def getNext(): Boolean = {
//      if (nextRecord == null) {
//        nextRecord = if (buffer != null && buffer.hasNext) {
//          buffer.next
//        } else {
//          poll(30000)
//        }
//      }
//
//      nextRecord != null
//    }
//
//    override def next(): ConsumerRecord[K, V] = {
//      if (!hasNext) {
//        throw new IllegalStateException("Can't call next() once there is no more records")
//      }
//      val record = nextRecord
//      offset = record.offset() + 1
//
//      nextRecord = null
//      record
//    }
//
//    /** poll records from kafka topic partition **/
//    private def poll(timeout: Long): ConsumerRecord[K, V] = {
//      var result : ConsumerRecord[K, V] = null
//
//      buffer = null
//      var finish = false
//      var reset = false // reset flag
//      while (!finish) {
//        if (reset) { // if reset flag is true, we need to do seek
//          consumer.seek(topicPartition, offset)
//          reset = false
//        }
//        val records = consumer.poll(timeout)
//        val iter = records.iterator()
//        if (iter.hasNext) {
//          result = iter.next
//          if (result.offset() > offset) {
//            log.warn(s"Cannot fetch records in [${offset}, ${result.offset})")
//            if (result.offset() >= part.untilOffset) {
//              throw new IllegalStateException(
//                s"Tried to fetch ${offset} but the returned record offset was ${result.offset} " +
//                  s"which exceeded untilOffset ${part.untilOffset}")
//            } else {
//              log.warn(s"Skip missing records in [$offset, ${result.offset})")
//            }
//          } else if (result.offset() < offset) {
//            throw new IllegalStateException(
//              s"Tried to fetch ${offset} but the returned record offset was ${result.offset}")
//          }
//          buffer = iter
//          finish = true
//        } else {
//          // We cannot fetch anything after `poll`. Two possible cases:
//          // - `offset` is out of range so that Kafka returns nothing.
//          // - Cannot fetch any data before timeout. TimeoutException will be thrown.
//          val range = getAvailableOffsetRange()
//          log.info(s"KafkaRDDIterator iterating: ${topicPartition}, " +
//            s"offset: ${offset}, available offset range: [${range.earliest}, ${range.latest})")
//          if (range.earliest == range.latest) {
//            log.warn(s"No more records, available offset range: [${range.earliest}, ${range.latest})")
//            offset = range.latest
//            finish = true
//          } else {
//            if (offset < range.earliest) {
//              if (range.earliest < part.untilOffset) {
//                log.warn(s"Skip missing records in [$offset, ${range.earliest})")
//                offset = range.earliest
//                reset = true
//              } else { // range.earliest >= part.untilOffset
//                throw new IllegalStateException(
//                  s"Tried to fetch [${offset}, ${part.untilOffset}) but the range earliest is ${range.earliest}")
//              }
//            } else if (offset >= range.latest) {
//              throw new IllegalStateException(
//                s"Tried to fetch ${offset} which exceeds the latest offset ${range.latest}.")
//            } else { // range.earliest <= offset < range.latest
//              throw new TimeoutException(
//                s"Cannot fetch record for offset ${offset} in ${timeout} milliseconds")
//            }
//          }
//        }
//      }
//      result
//    }
//
//    case class AvailableOffsetRange(earliest: Long, latest: Long)
//
//    /**
//      * Return the available offset range of the current partition. It's a pair of the earliest offset
//      * and the latest offset.
//      */
//    def getAvailableOffsetRange(): AvailableOffsetRange = {
//      consumer.seekToBeginning(Set(topicPartition).asJava)
//      val earliestOffset = consumer.position(topicPartition)
//      consumer.seekToEnd(Set(topicPartition).asJava)
//      val latestOffset = consumer.position(topicPartition)
//      AvailableOffsetRange(earliestOffset, latestOffset)
//    }
//  }

}
