package advanced.com

object Functions extends App {

  def calculate(x: (Int) => Int): Int => Int = (y:Int) => {
    val  y = x(10) + 10
    y + 10
  }

  def display(x:Int): Int = {
    x + 10
  }

  def enter(x:Int): Int = {
    x + 20
  }

  implicit def printer(x:Int): Unit = {
    println(x)
  }

  val function: Int = calculate( x => x+10 ) (display(10))
  println(function)

  def runAFunction(f: Int => Int)(implicit fx: Int => Unit): Unit = {
         val y = f(100)
         fx(y)
     }

  def printAnInt (i: Int): Int = { i + 10 }
  val ds: Unit = runAFunction(printAnInt)
  ds
}