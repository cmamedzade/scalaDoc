package basics.com

object Variances extends App {

  class Foo[+A] // A covariant class
  class Bar[-A] // A contravariant class
  class Baz[A]  // An invariant class

  // covariant example. Means all dogs ant cats are animals but animals are not dog or cat

  abstract class Animals {
    def name:String
  }

  def printAnimalNames(animals: List[Animals]): Unit =
    animals.foreach {
      animal => println(animal.name)
    }

  case class Dog(name: String) extends Animals
  case class Cat(name: String) extends Animals

  val cats: List[Cat] = List(Cat("kesha"),Cat("shushu"))
  val dogs: List[Dog] = List(Dog("toplan"), Dog("tori"))
  printAnimalNames(cats)
  printAnimalNames(dogs)

  // A contravariant class. Means all animals are dogs and cats and cats are also animals

  abstract class Printer[-A] {
    def print(data:A)
  }

  class AnimalPrinter extends Printer[Animals] {
    override def print(data: Animals): Unit =
      println(data)
  }

  class CatPrinter extends Printer[Cat] {
    override def print(data: Cat): Unit =
      println(data)
  }

  class DogPrinter extends Printer[Dog] {
    override def print(data: Dog): Unit =
      println(data)
  }

  def printMyCat(printer: Printer[Cat], cat: Cat): Unit = {
    printer.print(cat)
  }

  val catPrinter: Printer[Cat] = new CatPrinter
  val animalPrinter: Printer[Animals] = new AnimalPrinter
  val dogPrinter: Printer[Dog] = new DogPrinter
  printMyCat(catPrinter, Cat("shushu"))
  printMyCat(animalPrinter, Cat("Boots"))
 // printMyCat(dogPrinter,Dog("Dog"))  // it won't compile because dogs are not cats

  // invariance

  class Container[A](value: A) {
    private var _value: A = value
    def getValue: A = _value
    def setValue(value: A): Unit = {
      _value = value
    }
  }

  val catContainer: Container[Cat] = new Container(Cat("Felix"))
 //val animalContainer: Container[Animals] = catContainer  // will not compile
 // animalContainer.setValue(Dog("Spot"))
  val cat: Cat = catContainer.getValue // Oops, we'd end up with a Dog assigned to a Cat

}
