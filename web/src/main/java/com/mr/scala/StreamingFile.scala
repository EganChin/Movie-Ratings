package com.mr.scala

import java.io.{BufferedWriter, FileWriter}

import org.apache.spark._
import org.apache.spark.streaming._
/**
  * Created by zzy on 2019/6/20.
  */
object StreamingFile {

    def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("TestDStream").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(1))

        //此处为监视的文件夹， 可更换
    val lines = ssc.textFileStream("E:\\spark")

        lines.foreachRDD(rdd =>
            rdd.foreach { x =>

              //追加的文件路径，可更换
                var out = new BufferedWriter(new FileWriter("E:\\spark\\ml-latest\\ratings1.csv", true))
                out.write(x)
                out.write(System.getProperty("line.separator"))
                out.flush()
                out.close()
            }


        )
    ssc.start()
    ssc.awaitTermination()

  }


}
