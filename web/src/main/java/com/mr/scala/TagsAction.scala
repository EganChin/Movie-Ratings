package com.mr.scala

import java.text.SimpleDateFormat
import java.util.Date



/**
  * Created by zzy on 2019/6/26.
  */
class TagsAction extends HelloWorld{

  //查找某个电影的所有用户所打的标签按时间排序
  //    tags.filter(x => x.split(",")(1).equals("88129")).map(x => ((x.split(",")(0), x.split(",")(2)), x.split(",")(3)))
  //      .sortBy(_._2, false)
  //      .mapValues(x => tranTimeToString(x.toLong))
  //      .foreach(println)

  def getMoviesLastTag(id:String) : Unit = {
        tags.filter(x => x.split(",")(1).equals(id)).map(x => ((x.split(",")(0), x.split(",")(2)), x.split(",")(3)))
          .sortBy(_._2, false)
            .take(20)
          .foreach(println)
  }

}
