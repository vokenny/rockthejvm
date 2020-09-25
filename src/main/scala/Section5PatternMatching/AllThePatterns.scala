package Section5PatternMatching

import Section3OOP.{Cons, Empty, MyList}

object AllThePatterns extends App {

  // 1. Use of constants in PM
  val x: Any = "Scala"
  val constants: String = x match {
    case 1 => "A number"
    case "Scala" => "The Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2. Match anything
  // 2.1. Wildcard usage
  val matchAnything = x match {
    case _ => ???
  }

  // 2.2. Variable
  val matchVariable = x match {
    case something => s"I can use the something variable which says: $something"
  }

  // 2.3. Tuples
  val aTuple = (1, 2)
  val matchTuple = aTuple match {
    case (1, 1) => ???
    case (a, 2) => s"I found $a"
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (1, v) => s"Returns (2, 3): $v"
    case (_, (2, v)) => s"Returns 3: $v"
  }
  // PMs can be nested!

  // 4. Case classes - constructor pattern
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => ???
    case Cons(head, Cons(subhead, subtail)) => ???
    case Cons(h, t) => "" + h + t
  }

  // 5. List patterns
  val list = List(1, 2, 3, 42)
  val matchList = list match {
    case List(1, _, _, _) => // Extractor - advanced
    case List(1, _*) => // List of arbitrary length - advanced
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6. Type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7. Name binding
  val nameBindingMatch = aList match {
    case NonEmptyList @ Cons(_, _) => // name binding => use the name later (here)
    case Cons(1, rest @ Cons(2, _)) => // name binding in nested patterns
  }

  // 8. Multi-patterns
  val multiPattern = aList match {
    case Empty | Cons(0, Empty) => // Pipe operator acts as an OR
  }

  // 9. If guards
  val secondElemSpecial = aList match {
    case Cons(_, Cons(special, _)) if special % 2 == 0 => ???
  }

  // Question
  // Which string is returned?

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case strList: List[String] => "A list of String"
    case intList: List[Int] => "A list of Int"
  }

  // JVM trick question
  /*
  JVM was built to be backwards compatible so Java 1 programs could still work
  as a result, the JVM is oblivious to generic types.

  After type-checking, the types are erased and the JVM interprets the case matches like so:

  val numbersMatch = numbers match {
    case strList: List => "A list of String"
    case intList: List => "A list of Int"
  }

  This problem is called Type Erasure
  */

}
