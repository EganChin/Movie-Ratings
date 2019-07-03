package com.mr.scala

import java.io.PrintWriter

object WriteFile {
  def main(args: Array[String]): Unit = {

    for(i <- 1 to 1000000000){

      var out = new PrintWriter("E:\\spark\\ratings\\" + System.currentTimeMillis() + ".txt")

      //1,110,1.0,1425941529
      out.print("1,4963," + i % 5 + "," + System.currentTimeMillis())
      out.close()
      Thread.sleep(1000)
    }



  }
}