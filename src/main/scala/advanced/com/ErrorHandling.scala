package advanced.com

import scala.util.{Failure, Success, Try}

/*
  Option is superclass of Some and None
  Try is superclass of Failure and Success
  Either is superclass of Left and Right
 */

object ErrorHandling extends App {

  // Option

  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }

  toInt("4") match {
    case Some(i) => println(i)
    case None => println("That didn't work.")
  }

  val y = for {
    a <- toInt("1")
    b <- toInt("2")
    c <- toInt("3")
  } yield a + b + c

  // Try

  def toIntTry(s: String): Try[Int] = Try {
    Integer.parseInt(s.trim)
  }

  def toIntTryShort(s: String): Try[Int] = Try(Integer.parseInt(s.trim))  // it's result will be Success(value) or Failure(exception)

  toIntTry("1") match {
    case Success(i) => println(i)
    case Failure(s) => println(s"Failed. Reason: $s")
  }

  val yy = for {
    a <- toIntTryShort("1")
    b <- toIntTryShort("2")
    c <- toIntTryShort("3")
  } yield a + b + c

  // Either

  def divider( x: Int, y: Int): Either[String,Int] = {
    if ( y == 0 ) Left(s"divider can't be $y")
    else Right(x / y)
  }

  divider(1,0) match {
    case Left(s) => println(s)
    case Right(n) => println(n)
  }

}
