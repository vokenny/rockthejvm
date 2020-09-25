package Section5PatternMatching

import scala.util.Random

object PatternMatching extends App {

  val random = new Random()
  val x = random.nextInt(10)

  val description: String = x match {
    case 1 => "the ONE"
    case 2 => "Double or Nothing"
    case 3 => "Third time lucky"
    case _ => "Something" // _ = WILDCARD
  }

  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n, and I can't drink alcohol in the US"
    case Person(n, a) => s"Hi, my name is $n, and I am $a years old"
    case _ => "I don't know who I am"
  }

  println(greeting)

  /*
    1. Cases are matched in order from top to bottom
    2. MatchError is thrown for no matching cases
    3. Return type of the pattern match is the unified type of all the cases
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Chihuahua")

  // Will warn that PM is not exhaustive due to missing cases of sealed hierarchy
  animal match {
    case Dog(b) => println(s"Matched a dog of breed: $b")
  }

  // Exercise
  sealed trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  /*
    Simple function uses PM
    takes an Expr => human readable form

    Sum(Number(2), Number(3)) => 2 + 3
    Sum(Number(1), Sum(Number(2), Number(3))) => 1 + 2 + 3
    Prod(Sum(Number(2), Number(1)), Number(3)) => (2 + 1) * 3
    Sum(Prod(Number(2), Number(1)), Number(3)) => 2  * 1 + 3
   */

  def show(e: Expr): String = {
    e match {
      case Number(n) => n.toString
      case Sum(a, b) => show(a) + " + " + show(b)
      case Prod(a, b) =>
        def maybeShowParantheses(exp: Expr): String = {
          exp match {
            case Prod(_, _) => show(exp)
            case Number(_) => show(exp)
            case Sum(_, _) => "(" + show(exp) + ")"
          }
        }

        maybeShowParantheses(a) + " * " + maybeShowParantheses(b)
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Number(1), Sum(Number(2), Number(3)))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(2)))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))

}
