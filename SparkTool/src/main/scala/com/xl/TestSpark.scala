package com.xl

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

class TestSpark{

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    val spark = SparkSession.builder.appName("Test").master("local[1]").getOrCreate()



  }

}
