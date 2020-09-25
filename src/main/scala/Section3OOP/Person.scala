package Section3OOP

class Person(val name: String, age: Int = 0, favouriteMovie: String) {

  def likes(movie: String): Boolean = movie == favouriteMovie

  def +(nickName: String): Person = new Person(s"$name ($nickName)", age, favouriteMovie)

  def unary_+ : Person = new Person(name, age + 1, favouriteMovie)

  def learns(topic: String): String = name + " is learning " + topic

  def learnsScala: String = learns("Scala")

  def apply(n: Int): String = s"$name watched $favouriteMovie $n times"

}

object Person extends App {

  val mary = new Person("Mary", 20,"Inception")

  mary + "the rockstar"

  +mary

  mary learns "functional programming"

  mary learnsScala

  mary(2)

}