package basics.com

import scala.language.implicitConversions


object ImplicitConversion extends App {


   def int2Integer(x: Int) =
    java.lang.Integer.valueOf(x)  // java type Integer, compiler converts implicitly

  int2Integer(10)

  implicit def stringToInt(list: List[String]): List[Int] = {
    val intList: List[Int] = list.map {
      case "hello" => 10
      case "world" => 20
    }
    intList
  }

  val intList: List[Int] = List[String]("hello","world") // it will implicitly convert to int
  intList.foreach(println)

}
