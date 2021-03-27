package advanced.com

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext,Await}
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.{ Future, Promise }


object FuturesAndPromises extends App {

  def reader(word:String,url:String): Option[String] = {
    val read =  scala.io.Source.fromFile(url)
    read.getLines().find(line => line.contains(word))
  }
  val finder: Future[Option[String]] = Future{
    reader("Bytes","/home/ceyhun/Documents/exercies/SparkExample/course.csv")
  }

  val finderNext: Future[Option[String]] = Future{
    reader("Google","/home/ceyhun/Documents/exercies/SparkExample/course.csv")
  }

   finder.onComplete{
    case Success(line) => line
    case Failure(exception) => s"can't retrieve data: $exception"
  }

  finder foreach {
    case Some(data) => println(data)
    case None => println("nothing found")
  }

  val result: Future[String] = for (first <- finder;                //Functional Composition and For-Comprehensions
                                    second <- finderNext) yield {

    first + second.toString
  }

  result foreach { lines =>
    println(lines)
  }

  val recoverExample: Future[Serializable] = Future {  // recover another value
    reader("Ceyhun","/home/ceyhun/Documents/exercies/SparkExample/coure.csv")
  }.recover{
        case _:Throwable => "none"
      }

  recoverExample.foreach{
    line =>
      println(line)
  }

  val recoverWithExample: Future[Serializable] = Future {   // recover with another future
    reader("Ceyhun","/home/ceyhun/Documents/exercies/SparkExample/coure.csv")
  }.recoverWith{
    case _:Throwable => Future {
      reader("Google","/home/ceyhun/Documents/exercies/SparkExample/course.csv")
    }
  }.andThen{
    case Success(data) => println(s"from partial function: $data")
    case Failure(ex) => println(s"error: $ex")
  }

   Future {
    reader("Ceyhun","/home/ceyhun/Documents/exercies/SparkExample/coure.csv")
  }.fallbackTo{
     Future {
       reader("Google","/home/ceyhun/Documents/exercies/SparkExample/course.csv")
     }
  }.foreach(println)

  recoverWithExample.foreach{
    line =>
      println(s"this is line: $line")
  }

  val thenExample: Future[Serializable] = Future {       // recover with another future
    reader("Ceyhun","/home/ceyhun/Documents/exercies/SparkExample/coure.csv")
  }.andThen{
    case Success(data) => println(s"andThen example: $data")
    case Failure(ex) => println(s"andThen error: $ex")
  }

/*  implicit val ec = ExecutionContext.fromExecutor(
    Executors.newFixedThreadPool(2))

  val blockThreadInside: Future[IndexedSeq[Int]] = Future {  // block inside
    blocking[IndexedSeq[Int]]{
      for ( i <- 1 to  100) yield {
    //    Thread.sleep(10000)
        i
      }
    }
  }(ec)

    blockThreadInside
    .foreach(data => data.foreach(println))*/

    def calc(x:Int): Unit ={   // blocking outside
      val cl = Future {
        Thread.sleep(1000)
        x + 100
      }

      val wt= cl
        .map(data => println(s"value is: ${data * 2}"))

      Await.result(wt, 20 seconds)  // wait until result is ready  ready() this method is not wait on complete
    }

  calc(10)


  //-----------------------------

  val g = Future {   // failed method catch exception if there is not any exception it will do nothing
    4 / 2
  }
  for (exc <- g.failed) println(exc)

  //Thread.sleep(1000)

  //----------------------------- promise

  val promise = Promise[Int]()
  val future = promise.future

  val producer = Future {  // promise promises the computation matches the promise result it has success and failure methods
    val result = 4 / 2
    promise success result
    println(result * 10)
  }

  val consumer = Future {
    val x = 10 + 2
    future.foreach{
      r =>
        println(r+x)
    }
  }

  val producerWithFail = Future {
    val r = 4 / 0
    if (!r.isValidInt)
      promise failure (new ArithmeticException)
    else
      promise success r
    println(s"$r is succeeded")
  }

  val f = Future { 1 }
  val p = Promise[Int]()

  p completeWith f

  p.future foreach { x =>
    println(x)
  }

  def first[T](f: Future[T], g: Future[T]): Future[T] = {  // tryComplete, trySuccess and tryFailure.
    val p = Promise[T]

    f foreach { x =>  // one of f or g success method first will success if none of them fail method will fail
      p.trySuccess(x)
    }

    g foreach { x =>
      p.trySuccess(x)
    }

    p.future
  }

}
