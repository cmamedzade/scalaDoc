package basics.com

object Objects extends App {

  object Logger {                         // objects is single instance classes
    def info(log: String): String = {
      s"log message: $log"
    }
  }

  class LogWriter{
    import Logger._
    println("application started to collect logs")
    info("application started....")  // we can use objects methods from import
  }


  object Calculate {                        // companion object
   private def calculateArea( r:Int ): Double = {   // private methods can accessible from it's class
      3.14*2 * r
    }
  }

  case class Calculate(radius:Int) {
  import Calculate._
    def sumArea: Double = calculateArea(r = radius)
  }

  // factory method

  class Email(val username: String, val domainName:String)

  object Email {
    def fromString(emailString: String): Option[Email] = {

      emailString.split("@") match {
        case Array(a,b) =>  Some(new Email(a,b))
        case _ => None
      }
    }
  }

  val scalaCenterEmail = Email.fromString("scala.center@epfl.ch")
  scalaCenterEmail match {
    case Some(email) => println(
      s"""Registered an email
         |Username: ${email.username}
         |Domain name: ${email.domainName}
     """.stripMargin)
    case None => println("Error: could not parse email")
  }


}
