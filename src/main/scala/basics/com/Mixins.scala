package basics.com

object Mixins extends App {

  trait Iterables[A] {
    var current = 0
    def hasNext: Boolean
    def next: Int
  }

  trait IterateOver[A] {
    def forEach(f:A => Unit): Unit // the function takes function which returns unit
  }

  class Iterate(list: List[Int]) extends Iterables[Int] with IterateOver[Int] {
    override def hasNext: Boolean = current < list.size

    override def next: Int ={
        val temp = current
        current += 1
        list(temp)
      }

    override def forEach(f:Int => Unit): Unit = while (hasNext) f(next)
  }

  val itr = new Iterate(List(1,2,3,4,6))
  itr.forEach(println)
}
