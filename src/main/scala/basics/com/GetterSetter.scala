package basics.com

object GetterSetter extends App {

  class Car(price:Int, name:String){

    private var _x = price
    private var _y = name

    def x = _x
    def y = _y

    def x_= (i:Int): Unit = _x = i
    def y_= (s:String): Unit = _y = s
  }

  val c = new Car(100,"merc")
  println(s"${c.x}  ${c.y}")

  c.y = "opel"
  println(s"${c.x}  ${c.y}")
}
