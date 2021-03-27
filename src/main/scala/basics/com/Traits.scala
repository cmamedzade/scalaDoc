package basics.com

import scala.collection.mutable.ArrayBuffer

object Traits extends App {

  trait Iterator[A] {
    var current:Int = 0
    def hasNext(): Boolean
    def next(): Int
  }

  class MyIterator(to:Int) extends Iterator[Int] {
    override def hasNext(): Boolean =
      current < to

    override def next(): Int =
      if (hasNext()) {
        val temp:Int = current
        current += 1
        temp
      }
      else 0
  }

  val myIterator = new MyIterator(10)
  println(myIterator.next())
  println(myIterator.next())
  println(myIterator.next())

  trait Pet {
    val name:String
  }

  class Dog(val name: String) extends Pet
  class Cat(val name: String) extends Pet

  val animal = ArrayBuffer.empty[Pet]
  animal.append(new Dog("dog"))
  animal.append(new Cat("cat"))
  animal.foreach(anml => println(anml.name))
}
