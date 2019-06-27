package com.mr.scala

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zzy on 2019/6/15.
  */
class HelloWorld {

    var conf = new SparkConf().setAppName("first").setMaster("local");

    conf.set("spark.driver.allowMultipleContexts", "true")

    var sc = new SparkContext(conf)

    //电影  movieId,title,genres
    var movies = sc.textFile("E:\\spark\\ml-latest\\movies.csv");

    //用户观看对电影的评级 userId,movieId,rating,timestamp
    var ratings = sc.textFile("E:\\spark\\ml-latest\\ratings1.csv");

    //用户对该电影打的标签 userId,movieId,tag,timestamp
    var tags = sc.textFile("E:\\spark\\ml-latest\\tags.csv");

    var users = sc.textFile("E:\\spark\\ml-latest\\users.csv");


    def main(args: Array[String]): Unit = {

        //    var conf = new SparkConf().setAppName("first").setMaster("local");
        //
        //    var sc = new SparkContext(conf)
        //
        //    //电影  movieId,title,genres
        //    var movies = sc.textFile("C:\\Users\\zzy\\Desktop\\spark要求111\\spark\\ml-latest\\movies.csv");
        //
        //    //用户观看对电影的评级 userId,movieId,rating,timestamp
        //    var ratings = sc.textFile("C:\\Users\\zzy\\Desktop\\spark要求111\\spark\\ml-latest\\ratings1.csv");
        //
        //    //用户对该电影打的标签 userId,movieId,tag,timestamp
        //    var tags = sc.textFile("C:\\Users\\zzy\\Desktop\\spark要求111\\spark\\ml-latest\\tags.csv");
        //
        //    var users = sc.textFile("C:\\Users\\zzy\\Desktop\\spark要求111\\spark\\ml-latest\\users.csv");

        //用户所有评论记录 rdd -----------------
        //    var ms = movies.map(x => (x.split(",")(0), x)).collectAsMap()
        //
        //    ratings.filter(x => x.split(",")(0).equals("8"))
        //      .map(x => x.split(",")(1))
        //      .map(x => (x, ms.get(x))).foreach(println)

        //通过歌曲名称ID查看电影----------------
        //    movies.filter(x => x.split(",")(0).equals("8")).foreach(println)

        //通过ID查询用户信息----------------
        //    users.filter(x => x.split(",")(0).equals("40")).foreach(println);

        //通过歌曲名称模糊查看电影---------------
        //    movies.filter(x => x.split(",")(1).contains("1999")).foreach(println)

        //通过歌曲前缀名称模糊查看电影-------------
        //    movies.filter(x => x.split(",")(1).startsWith("Va")).foreach(println)

        //通过歌曲后缀名称模糊查看电影------------------
        //    movies.filter(x => x.split(",")(1).endsWith("Street")).foreach(println)

        //通过类型进行模糊查询电影-----------------
        //    movies.filter(x => x.split(",")(2).contains("Sci-Fi")).foreach(println)

        //按照用户评价降序前20条----------------
        //    ratings.filter(x => !x.split(",")(0).equals("userId"))
        //        .map(x => (x.split(",")(1), x.split(",")(2))).mapValues(x => (x, 1))
        //      .reduceByKey((x, y) => ((x._1.toDouble + y._1.toDouble).toString, x._2 + y._2))
        //      .mapValues(x => x._1.toDouble / x._2.toDouble)
        //      .sortBy(_._2,false).take(20)
        //      .foreach(println)

        //用户最新打分动态
        //    ratings.filter(x => !x.split(",")(0).equals("userId"))
        //      .map(x => (x.split(",")(1), x.split(",")(3).toLong))
        //      .sortBy(_._2,false)
        //      .mapValues(x => tranTimeToString(x))
        //      .foreach(println)


        //查找某个电影的所有用户所打的标签按时间排序
        //    tags.filter(x => x.split(",")(1).equals("88129")).map(x => ((x.split(",")(0), x.split(",")(2)), x.split(",")(3)))
        //      .sortBy(_._2, false)
        //      .mapValues(x => tranTimeToString(x.toLong))
        //      .foreach(println)

        //电影评论次数排序前20（通过评论数来反映）
        //    ratings.filter(x => !x.split(",")(0).equals("userId")).map(x => (x.split(",")(1), 1))
        //      .reduceByKey((x, y) => x + y)
        //      .sortBy(_._2,false)
        //      .take(20)
        //      .foreach(println)


        def tranTimeToString(tm: Long): String = {
            val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val tim = fm.format(new Date(tm))
            tim
        }

    }

}
