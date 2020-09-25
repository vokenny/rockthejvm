package Section3OOP

object Exceptions extends App {

  class OverFlowException extends RuntimeException
  class UnderFlowException extends RuntimeException
  class MathCalcException extends RuntimeException

  object PocketCalc {

    def add(x: Int, y: Int): Int = {
      val r: Int = x + y
      if (x > 0 && y > 0 && r < 0) throw new OverFlowException
      else if (x < 0 && y < 0 && r > 0) throw new UnderFlowException
      else r
    }

    def subtract(x: Int, y: Int): Int = {
      val r: Int = x - y
      if (x > 0 && y < 0 && r < 0) throw new OverFlowException
      else if (x < 0 && y > 0 && r > 0) throw new UnderFlowException
      else r
    }

    def multiply(x: Int, y: Int): Int = {
      val r = x * y
      if (x > 0 && y > 0 && r < 0) throw new OverFlowException
      else if (x < 0 && y < 0 && r < 0) throw new OverFlowException
      else if (x < 0 && y > 0 && r > 0) throw new UnderFlowException
      else if (x > 0 && y < 0 && r > 0) throw new UnderFlowException
      else r
    }

    def divide(x: Int, y: Int): Int = if (y == 0) throw new MathCalcException else x / y

  }

  try {
    val result = PocketCalc.add(Int.MaxValue, 1)
    println(result)
  } catch {
    case e: OverFlowException => println
    case e: UnderFlowException => println
  } finally {
    println("finally")
  }

}
