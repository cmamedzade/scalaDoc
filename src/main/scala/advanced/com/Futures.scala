package advanced.com

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Futures extends App {

  def sumDirect(data:Int):Int = {                 // it will run on the current thread
    data * 10
  }

  def sum(data:Int): Future[Int] = {               // it assign another thread to process
    Future{ Thread.sleep(10 * 100); data+10}
  }

  println(sum(10))   // it throws not completed. Because thread on it's way.

  val resultT: Unit = sum(10).onComplete{
    case Failure(ex) => println(s"can't process data: $ex")
    case Success(data) => println(s"the result is: $data")
  }

  Thread.sleep(10 * 100)

  resultT

  // use this to determine the “delta time” below
  val startTime = currentTime

  // (a) create three futures
  val aaplFuture = getStockPrice("AAPL")
  val amznFuture = getStockPrice("AMZN")
  val googFuture = getStockPrice("GOOG")

  // (b) get a combined result in a for-expression
  val result: Future[(Double, Double, Double)] = for {
    aapl <- aaplFuture
    amzn <- amznFuture
    goog <- googFuture
  } yield (aapl, amzn, goog)

  // (c) do whatever you need to do with the results
  result.onComplete {
    case Success(x) => {
      val totalTime = deltaTime(startTime)
      println(s"In Success case, time delta: ${totalTime}")
      println(s"The stock prices are: $x")
    }
    case Failure(e) => e.printStackTrace
  }

  // important for a short parallel demo: you need to keep
  // the jvm’s main thread alive
  sleep(5000)

  def sleep(time: Long): Unit = Thread.sleep(time)

  // a simulated web service
  def getStockPrice(stockSymbol: String): Future[Double] = Future {
    val r = scala.util.Random
    val randomSleepTime = r.nextInt(3000)
    println(s"For $stockSymbol, sleep time is $randomSleepTime")
    val randomPrice = r.nextDouble * 1000
    sleep(randomSleepTime)
    randomPrice
  }

  def currentTime = System.currentTimeMillis()
  def deltaTime(t0: Long) = currentTime - t0

}
