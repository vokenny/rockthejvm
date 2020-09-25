package Section5PatternMatching

object PatternsEverywhere extends App {

  // Big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "Something else"
  }

  // "catch" is actually "match"
  /*
  try {
  } catch (e) {
    e match {
      case e: RuntimeException => "runtime"
      case npe: NullPointerException => "npe"
      case _ => "Something else"
    }
  }
   */

  // Big idea #2
  val list = List(1, 2, 3, 4)
  val evens = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x

  // Generators are based on PM
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // case classes, infix operators, etc all work as well

  // Big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple // name binding
  // multiple value definitions on PM

  val head :: tail = list

  // Big idea #4
  // Partial function based on PM
  val mappedList = list.map {
    case v if v % 2 == 0 => v + "is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal
  //which is equivalent to...
  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + "is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

}
