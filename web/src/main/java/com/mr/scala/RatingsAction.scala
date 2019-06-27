package com.mr.scala



/**
  * Created by zzy on 2019/6/26.
  */
class RatingsAction extends HelloWorld with java.io.Serializable{

  //按照用户评降序前20条
  //    ratings.filter(x => !x.split(",")(0).equals("userId"))
  //        .map(x => (x.split(",")(1), x.split(",")(2))).mapValues(x => (x, 1))
  //      .reduceByKey((x, y) => ((x._1.toDouble + y._1.toDouble).toString, x._2 + y._2))
  //      .mapValues(x => x._1.toDouble / x._2.toDouble)
  //      .sortBy(_._2,false).take(20)
  //      .foreach(println)

  def getRatingsNew20() : Array[(String, Double)] ={
        var re = ratings.filter(x => !x.split(",")(0).equals("userId"))
          .map(x => (x.split(",")(1), x.split(",")(2))).mapValues(x => (x, 1))
          .reduceByKey((x, y) => ((x._1.toDouble + y._1.toDouble).toString, x._2 + y._2))
          .mapValues(x => x._1.toDouble / x._2.toDouble)
          .sortBy(_._2,false).take(20)

    re
  }

}
