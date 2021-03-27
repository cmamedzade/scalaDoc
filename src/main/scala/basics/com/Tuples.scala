package basics.com

object Tuples extends App {

  val tuple = ("Price",100)
  println(tuple._2)

  val (name,quantity) = tuple      // tuple pattern match
  println(name)

  val planets = List(("Earth",5), ("Moon",2), ("Mercury",1))
  planets.foreach{
    case ("Earth", order) => println(s"the Earth is $order th planet")
    case ("Mercury", order) => println(s"the Mercury is $order th planet")
    case (planet, _) => println(s"$planet is not a planet")
  }

  val (nm, price, release) = ("Apple","1200$",1987)
  println(price)

}
