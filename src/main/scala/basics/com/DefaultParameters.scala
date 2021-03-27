package basics.com

object DefaultParameters extends App {

  class Product(name:String = "mobile phone"){  // mobile phone is default parameter
    private var _value = name
    def value = _value
    def value_= (parameter: String): Unit = {
      _value = parameter
    }
  }

  val prod = new Product()
  println(prod.value)
}
