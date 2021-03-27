package basics.com

object CaseClasses extends App {

  case class Car(name:String, price:String)

  val ford = Car("ford","1200$")
  val anotherFord = Car("ford","1200$")
  println(s"price of ${ford.name} is ${ford.price}")

  val mather: Boolean = ford == anotherFord

  val fiat = ford.copy(name = "fiat")
  println(s"price of ${fiat.name} is ${fiat.price}")
}
