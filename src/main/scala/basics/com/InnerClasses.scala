package basics.com

object InnerClasses extends App {

  class Products{

    class Notebooks {
      val name: String = "Samsung"
    }

    def display(): Unit = {
      val notebooks = new Notebooks
    println(notebooks.name)
    }

  }

  val product = new Products
  product.display()

}
