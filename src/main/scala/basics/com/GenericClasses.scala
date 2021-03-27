package basics.com

object GenericClasses extends App {

  class ListItems[A] {
    var list: List[A] = Nil
    def addElement(element: List[A]): List[A] = {    
     list = list ::: element
      list
    }
  }
  
  val numbers = new ListItems[Int]
  val digits = numbers.addElement(List(1,2,3,4,5))
  val chars = new ListItems[Char]
  val characters = chars.addElement(List('a','b','c','v'))

  val pairs: Seq[(Int, Char)] = for (a <- digits;
                                     b <- characters) yield (a,b)

  pairs.foreach(data => println(s"${data._1} -- ${data._2}"))
  

}