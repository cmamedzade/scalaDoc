package advanced.com
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import java.io.PrintWriter

object TestFuture extends App {
    val list = List(1,2,3,4)
    val future = Future {
      Thread.sleep(20000)
      new PrintWriter("C:\\Users\\jeyhunmm\\Documents\\ScalaTests\\test.txt")
      { write(list.toString()); close() }
    }
    future.recover{
      case _:Throwable => "file not found"
    }
}