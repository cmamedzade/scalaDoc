package basics.com


object UpperTypeBounds extends App {

  abstract class Animal {
    def name: String
  }

  abstract class Pet extends Animal {}


  class Cat extends Pet {
    override def name: String = "Cat"
  }

  class Dog extends Pet {
    override def name: String = "Dog"
  }

  class PetContainer[P <: Pet](p: P){   // it requires Pet's subclasses
    def pet: P = p
  }

  class Lion extends Animal {
    override def name: String = "Lion"
  }

  val catContainer = new PetContainer[Pet](new Cat)
  val dogContainer = new PetContainer[Dog](new Dog)
  //val lionContainer = new PetContainer[Lion](new Lion)  // will not compile
}
