package Section4FP

object AnonymousFunctions extends App {

  // Anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2

  // Multiple params in Lambda
  val adder: (Int, Int) => Int = (x, y) => x + y

  val justDoSomething: () => Int = () => 42

  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with Lambda
  val stringToInt: String => Int = { string =>
    string.toInt
  }

  val niceIncrementer: Int => Int = _ + 1
  val niceAdder: (Int, Int) => Int = _ + _


}
