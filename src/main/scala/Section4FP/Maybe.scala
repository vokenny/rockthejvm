package Section4FP

abstract class Maybe[+T] {

  def map[A](f: T => A): Maybe[A]
  def flatMap[A](f: T => Maybe[A]): Maybe[A]
  def filter(p: T => Boolean): Maybe[T]

}

case object MaybeNot extends Maybe[Nothing] {

  override def map[A](f: Nothing => A): Maybe[A] = MaybeNot
  override def flatMap[A](f: Nothing => Maybe[A]): Maybe[A] = MaybeNot
  override def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot

}

case class Just[+T](value: T) extends Maybe[T] {

  override def map[A](f: T => A): Maybe[A] = Just(f(value))
  override def flatMap[A](f: T => Maybe[A]): Maybe[A] = f(value)
  override def filter(p: T => Boolean): Maybe[T] = if (p(value)) this else MaybeNot

}

object MaybeTest extends App {

  val justThree = Just(3)
  println(justThree)
  println(justThree.map(_ * 2))
  println(justThree.flatMap(x => Just(x % 2 == 0)))
  println(justThree.filter(_ % 2 == 0))

}