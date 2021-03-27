package basics.com

object Example extends App {


  val list = Array(3,2,4)
  val x = 6


  def finder(nums:Array[Int], n:Int): Array[Int] = {
    var a  = 0
    var b = 0
    nums.find{data =>
      nums.find { next  =>
          next + data == n
      } match {
        case Some(d) =>
          b = d
          true
        case None => false
      }
    } match {
      case Some(v) =>
        a = v
        true
      case None => false
    }

   Array(list.indexOf(a),list.indexOf(b))
  }

  var a = 0
  var b = a + 1
  def find(list: Array[Int], target: Int): Array[Int] = {
     list(a) + list(b) match {
      case target => Array(a, b)
      case _ =>
        a = a + 1
        b = a + 1
        find(list,target)
    }

  }

  find(list,x).foreach(println)

}
