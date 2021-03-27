package basics.com

object HighOrderFunctions extends App {

  val salary = List(1,2,3,4,5)
  salary.map(_ * 3)

  def increase(x:Int): Int = {
    x * 3
  }
  salary.map(increase)          // map will send each int value to increase method

  def calculate(list:List[Int], f: Int => Int): List[Int] = {  // reduce repeated codes
    list.map(f)
  }

  val l = calculate(List(1,2,3,4), (x:Int) => x * 10 )
  l.foreach(println)

  val x = calculate(List(1,2,3,4), (x:Int) => x + 10)
  x.foreach(println)

  def getUrl(ssl:Boolean, domainName:String): (String, String) => String = {  // function that returns function (String, String) => String  is function
    val schema = if ( ssl ) "https://" else "http://"
    (endpoint: String, query: String) => s"$schema$domainName/$endpoint?$query"
  }

  val url = getUrl(true,"www.unibank.az")("users","id=1")
  println(url)

  def calculator (a:Int, b:Int): (Int) => Int = {
    val s = a * b
    (v:Int) => s + v
  }

  val test = calculator(10,20)(30)
  println(test)

}
