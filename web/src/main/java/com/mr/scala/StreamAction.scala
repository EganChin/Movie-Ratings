package com.mr.scala

import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Date


/**
  * Created by zzy on 2019/6/26.
  */
class StreamAction extends HelloWorld {

    //电影评论次数排序前20（通过评论数来反映）
    //    ratings.filter(x => !x.split(",")(0).equals("userId")).map(x => (x.split(",")(1), 1))
    //      .reduceByKey((x, y) => x + y)
    //      .sortBy(_._2,false)
    //      .take(20)
    //      .foreach(println)

    def getMaxRemarkNumMovie(): Array[(String, Int)] = {
        ratings.filter(x => !x.split(",")(0).equals("userId")).map(x => (x.split(",")(1), 1))
            .reduceByKey((x, y) => x + y)
            .sortBy(_._2, false)
            .take(20)
    }


    //用户最新打分动态
    //    ratings.filter(x => !x.split(",")(0).equals("userId"))
    //      .map(x => (x.split(",")(1), x.split(",")(3).toLong))
    //      .sortBy(_._2,false)
    //      .mapValues(x => tranTimeToString(x))
    //      .foreach(println)
    def getLastRatingRecords(): Array[(String, Long)] = {
        ratings.filter(x => !x.split(",")(0).equals("userId"))
            .map(x => (x.split(",")(1), x.split(",")(3).toLong))
            .sortBy(_._2, false)
            .take(20)
    }

    /**
      * 以文件流的方式写入文件
      */
    def writeByStream(userId: String, movieId: String, score: String): Unit = {


        //此处是写的文件的路径文件夹，可更换
        var out = new PrintWriter("E:\\spark\\ratings\\" + System.currentTimeMillis() + ".txt")

        //1,110,1.0,1425941529
        out.print(userId + "," + movieId + "," + score + "," + System.currentTimeMillis())
        out.close()
        Thread.sleep(1000)


    }


}
