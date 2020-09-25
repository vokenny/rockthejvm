package Section3OOP

trait MyPredicate[-T] {

  def test(input: T): Boolean

}
