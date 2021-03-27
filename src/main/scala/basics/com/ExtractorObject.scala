package basics.com

import scala.util.Random

object ExtractorObject extends App {

  object CustomerId {
    def apply(name:String): String = s"$name -- ${Random.nextInt()}" // it takes parameter and constructs object

    def unapply(arg: String): Option[Array[String]] = {
      val stringArray: Array[String] = arg.split("--")
      if ( stringArray.tail.nonEmpty ) Some(stringArray) else None
    }
  }

  val customerId = CustomerId("unibank")  // we called apply method
  println(s"customerId is: $customerId")

  customerId match {                         // if we use match pattern then we call unapply method
    case CustomerId(name) => println(name(1))  // we can extract first array or second array
    case _ => println("customerId is not valid")
  }

  val CustomerId(name2) = "-- 1649382822"   // matches
//  val CustomerId(name3) = "- 164932822"   // throws match error

  val CustomerId(name4) = customerId  //unapply method implemented
  name4.foreach(println)  // we implemented unapply method to extract result of apply method

}
