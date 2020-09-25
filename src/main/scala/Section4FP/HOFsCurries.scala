package Section4FP

object HOFsCurries extends App {

  // Higher order function (HOF)
  // takes or returns a function
  // val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  // map, flatMap, filter are HOFs

  // function that applies a function n times on value x
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x else nTimes(f, n - 1, f(x))
  }

  println(nTimes(_ * 2, 3, 2)) // 16

  val plusOne: Int => Int = _ + 1

  println(nTimes(plusOne, 5, 0))

  def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plusTen: Int => Int = nTimesBetter(plusOne, 10)
  println(plusTen) // lambda
  println(plusTen(20)) //30

  // Curried functions
  val superAdder: Int => (Int => Int) = x => y => x + y
  val add3 = superAdder(3)
  println(add3(10)) // 13
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  def toCurry(f: (Int, Int) => Int): Int => Int => Int = {
    x: Int => y: Int => f(x, y)
  }

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int = {
    (x: Int, y: Int) => f(x)(y)
  }

  def compose[A, B, T](f: A => B, g: T => A): T => B = (x: T) => f(g(x))
  def andThen[A, B, T](f: T => A, g: A => B): T => B = (x: T) => g(f(x))

  def superAdder2: Int => Int => Int = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder2)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
