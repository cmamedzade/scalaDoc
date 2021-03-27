package basics.com
import scala.util.matching.Regex

object RegularExpressions extends App {

  val passwordMatch: Regex = "[0-9]".r

  passwordMatch.findFirstMatchIn("awesomepassword1") match {
    case Some(_) => println("password is ok")
    case None => println("password should contain digits")
  }

  val keyValPattern: Regex = "([0-9a-zA-Z- ]+): ([0-9a-zA-Z-#()/. ]+)".r  // for group pattern use parentheses

  val input: String =
    """background-color: #A03300;
      |background-image: url(img/header100.png);
      |background-position: top center;
      |background-repeat: repeat-x;
      |background-size: 2160px 108px;
      |margin: 0;
      |height: 108px;
      |width: 100%;""".stripMargin

  for (patternMatch <- keyValPattern.findAllMatchIn(input))
    println(s"key: ${patternMatch.group(1)} value: ${patternMatch.group(2)}")



}
