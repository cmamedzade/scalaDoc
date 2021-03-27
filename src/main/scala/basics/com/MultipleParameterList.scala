package basics.com

object MultipleParameterList extends App {

  val list = List(1,2,3,4,5)
  val res = list.foldLeft("0")((m,n) => s"$m  $n")
  println(res)

  def calculate(list:List[Int],a:Int): List[Int] = {
    list.map(data => data + a)  // 2,3,4
  }

  val result = list.foldLeft(List(1,2,3))(calculate)
  result.foreach(println)

}
