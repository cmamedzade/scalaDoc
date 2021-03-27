package basics.com

object LowerTypeBounds extends App {

  abstract class Animal{
    def name: String
  }

  abstract class Pet extends Animal{}

  class Cat extends Pet {
    override def name: String = "Cat"
  }

  class Dog extends Pet {
    override def name: String = "Dog"
  }

  class PetContainer[P >: Dog](p: P) {
    def display: P = p
  }

  val petContainer = new PetContainer[Pet](new Pet {
    override def name: String = "Pet"
  })

  val dogContainer = new PetContainer[Dog]( new Dog)
//  val catContainer = new PetContainer[Cat](new Cat)  // will not compile because Dog is not superclass of Cat


}
