package basics.com

object PolymorphicMethod extends App {

  def display[A](lines:A): List[A] = {
    List[A](lines)
  }

  val int1: List[Int] = display[Int](1)
  val listInt: List[Int] = List(1,2,3,4) ::: int1
  listInt.foreach(println)

  val str: List[String] = display[String]("hello")
  val listStr: List[String] = str ::: List("world")
  listStr.foreach(println)
}
