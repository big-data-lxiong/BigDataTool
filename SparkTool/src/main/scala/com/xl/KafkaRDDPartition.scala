package com.xl

import org.apache.kafka.common.TopicPartition
import org.apache.spark.Partition

case class KafkaRDDPartition (
                          val index: Int,
                          val tp: TopicPartition,
                          val untilOffset: Long
                        ) extends Partition