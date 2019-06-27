package com.mr.scala



/**
  * Created by zzy on 2019/6/26.
  */
class MoviesAction extends HelloWorld{


  //通过歌曲名称ID查看电影
  //    movies.filter(x => x.split(",")(0).equals("8")).foreach(println)

  def getMovieById(id: String): Array[String] = {
    movies.filter(x => x.split(",")(0).equals(id)).collect()
  }



  //通过歌曲名称模糊查看电影
  //    movies.filter(x => x.split(",")(1).contains("1999")).foreach(println)
  def getMovieByObsName(name: String): Array[String] = {
    movies.filter(x => x.split(",")(1).contains(name)).collect()
  }


  //通过歌曲前缀名称模糊查看电影
  //    movies.filter(x => x.split(",")(1).startsWith("Va")).foreach(println)
  def getMovieByPreObsName(name: String): Array[String] = {
    movies.filter(x => x.split(",")(1).startsWith(name)).collect()
  }

  //通过歌曲后缀名称模糊查看电影
  def getMovieByPostObsName(name: String): Array[String] = {
    movies.filter(x => x.split(",")(1).endsWith(name)).collect()
  }

  //通过类型进行模糊查询电影
  //    movies.filter(x => x.split(",")(2).contains("Sci-Fi")).foreach(println)
  def getMovieByType(name: String): Array[String] = {
    movies.filter(x => x.split(",")(2).contains(name)).collect()
  }


}
