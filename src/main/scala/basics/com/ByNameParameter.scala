package basics.com

/*
By-name parameters are evaluated every time they are used.
They won’t be evaluated at all if they are unused.
This is similar to replacing the by-name parameters with the passed expressions.
They are in contrast to by-value parameters.
To make a parameter called by-name, simply prepend => to its type.
 */


object ByNameParameter extends App {

  def calculate( x: => Int)(y: Int): Int = y * 10

// calculate(_)(10) // will not compile. Because byNameParameter is missing
  calculate(10)(_) // is correct

}
