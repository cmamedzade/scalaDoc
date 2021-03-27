package basics.com

object ForComprehensions extends App {

  case class Users(name:String, age:Int)

  val users: List[Users] = List(
    Users("Travis", 28),
    Users("Kelly", 33),
    Users("Jennifer", 44),
    Users("Dennis", 23))


    val userFilter: List[String] = for (user <- users if user.age >=20 && user.age < 30) yield user.name

  userFilter.foreach(println)

  def foo(n:Int, m:Int): Seq[(Int, Int)] = for (a <- 0 until n;
                                                b <- 10 until m) yield (a,b)

  foo(10,20).foreach(println)

}
