package com.mr.scala

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap

/**
  * Created by zzy on 2019/6/26.
  */
class UsersAction extends HelloWorld {


  //用户所有评论记录
  /**
    *
    * @param id 用户ID
    * @return
    */
  def getAllRemarkRecords(id: String): scala.collection.Map[String, Object] = {
    var ms = movies.map(x => (x.split(",")(0), x)).collectAsMap()
    ratings.filter(x => x.split(",")(0).equals(id))
      .map(x => x.split(",")(1))
      .map(x => (x, ms.get(x))).collectAsMap()
  }

  def getUserByID(id: String): Array[String] = {
    users.filter(x => x.split(",")(0).equals(id)).collect()
  }










}
