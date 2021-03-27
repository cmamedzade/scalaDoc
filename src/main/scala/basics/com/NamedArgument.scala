package basics.com

object NamedArgument extends App {

  class Internet(provider:String = "CityNet", speed:Int) {
    private var _pro = provider
    private var _spd = speed
    def pro = _pro
    def spd = _spd
  }

  val internet = new Internet(speed = 100)
  println(internet.spd)
}
