package basics.com

object ImplicitParameters extends App {

  abstract class Values[A] {
    def display: List[A]
  }

  implicit val list: List[String] = List("Hello","World!")

  class ListValues(implicit val list: List[String]) extends Values[String] {
    override def display: List[String] = list
  }

  val lister = new ListValues()    // implicitly will send value
  lister.display.foreach(println)
}
