package com.mr.scala

import org.apache.spark.sql.Row
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zzy on 2019/6/28.
  */
class SQLAction extends HelloWorld {

//  val conf = new SparkConf()
//  conf.setAppName("Wow,My First Spark Programe1")
//  conf.setMaster("local")

//  val sc = new SparkContext(conf)
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession.builder().getOrCreate()

  val moviesDF = spark.read.format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/mr")
    .option("driver", "com.mysql.jdbc.Driver")
    .option("dbtable", "movies")
    .option("user", "root")
    .option("password", "123456").load()

  val usersDF = spark.read.format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/mr")
    .option("driver", "com.mysql.jdbc.Driver")
    .option("dbtable", "users")
    .option("user", "root")
    .option("password", "123456").load()

  val tagsDF = spark.read.format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/mr")
    .option("driver", "com.mysql.jdbc.Driver")
    .option("dbtable", "tags")
    .option("user", "root")
    .option("password", "123456").load()

  val ratingsDF = spark.read.format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/mr")
    .option("driver", "com.mysql.jdbc.Driver")
    .option("dbtable", "ratings")
    .option("user", "root")
    .option("password", "123456").load()

  usersDF.createOrReplaceTempView("users")

  moviesDF.createOrReplaceTempView("movies")

  tagsDF.createOrReplaceTempView("users")

  ratingsDF.createOrReplaceTempView("ratings")

  ratingsDF.join(moviesDF, "movieid").join(usersDF, "userid").createOrReplaceTempView("umr")


  /**
    * 可以通过饼图展示，此接口主要写的是各个性别用户的总体打分情况
    * @return
    */
  def getAverRatingGender():Array[Row]={
    ratingsDF.join(usersDF, "userid").createOrReplaceTempView("userandratings")

    spark.sql("select Gender, AVG(rating) from userandratings group by Gender").collect()
  }

  /**
    * 查看每个类型电影的平均得分
    * @return
    */
  def getAverRatingByType():Array[Row]={
    ratingsDF.join(moviesDF, "movieId").createOrReplaceTempView("moviesuser")

    spark.sql("select SUBSTRING_INDEX(genres, '|',1) as type, AVG(rating) as score from moviesuser group by type order by type").collect()
  }

  def getAverRatingByTypeAndGender():Array[Row]={
    spark.sql("SELECT SUBSTRING_INDEX(genres, '|', 1) as type ,gender,AVG(rating) as score from umr group by type, gender order by type").collect()
  }

  def getLoveTypeByOccupation():Array[Row]={
    spark.sql("SELECT SUBSTRING_INDEX(genres, '|', 1) as type, occupation, AVG(rating) as score from umr group by occupation,type").createOrReplaceTempView("one")

    spark.sql("select occupation, MAX(score) as maxscore from one group by occupation").createOrReplaceTempView("two")

    spark.sql("select a.type, a.occupation, a.score from one a, two b where a.occupation = b.occupation and a.score = b.maxscore").collect()
  }



}
