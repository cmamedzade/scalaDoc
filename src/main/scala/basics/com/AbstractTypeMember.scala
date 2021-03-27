package basics.com

object AbstractTypeMember extends App {

  trait Buffer {
    type T           // abstract type
    val element: T
  }

  abstract class SeqBuffer extends Buffer {
    type U
    type T <: Seq[U]
    def length = element.length
  }

  abstract class IntSeqBuffer extends SeqBuffer {
    type U = Int
  }

  def newIntSeqBuffer(e1:Int, e2:Int): IntSeqBuffer = new IntSeqBuffer {
     type T = List[U]
     val element = List(e1, e2)
  }

  val buffer = newIntSeqBuffer(2,3)
  println(s"length: ${buffer.length}  element: ${buffer.element}")

}
