package Section4FP

object WhatsAFunction extends App {

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  val stringToIntConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("3") + 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(adder(1, 2))

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS (INSTANCES OF CLASSES)

  val concatenate: (String, String) => String = (string1: String, string2: String) => string1 + string2
  println(concatenate("Scala ", "rocks!"))

  // Currying
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }

  val superAdderShortForm: Int => Int => Int = x => y => x + y

  println(superAdder(3)(4))
  println(superAdderShortForm(3)(4))

}

trait MyFunction[A, B] {
  def apply(element: A): B = ???
}