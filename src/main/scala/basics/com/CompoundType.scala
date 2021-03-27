package basics.com

object CompoundType extends App {

  trait Cloneable extends java.lang.Cloneable {
    override def clone(): Cloneable = {
      super.clone().asInstanceOf[Cloneable]
    }
  }
  trait Resettable {
    def reset(): Unit
  }

  def cloneAndReset(obj: Cloneable with Resettable): Cloneable = {  // compound type
    val cloned = obj.clone()
    obj.reset()
    cloned
  }
}
