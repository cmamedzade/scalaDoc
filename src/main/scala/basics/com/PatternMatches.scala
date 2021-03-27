package basics.com

object PatternMatches extends App {

  val x = 10
  val y = x match {
    case 10 => "ten"
    case 0 => "zero"
    case _ => "any"
  }
  println(y)

  trait Phones    // we can specify sealed keyword. then all subclasses should be in the same file
  case class Iphone(price:Int) extends Phones
  case class Nokia(price:Int) extends Phones

  def printer(data: Phones): Unit ={
    data match {
      case value: Iphone  => println(s"price Iphone is: ${value.price}")
      case Nokia(a) => println(s"price Nokia is: $a")
    }
  }

  printer(Iphone(10))

  def typeFinder(data: Phones): String = {
    data match {
      case _:Iphone => s"class is Iphone"
      case _:Nokia => s"class is Nokia"
    }
  }

  println(typeFinder(Nokia(10)))

  def patternGuard(data:Phones) = {
    data match {
      case Iphone(a) if a < 10 => s"price of iphone is under $a"
      case Iphone(a) if a >= 10 => s"price of iphone is more than $a"
    }
  }

  println(patternGuard(Iphone(9)))



}
