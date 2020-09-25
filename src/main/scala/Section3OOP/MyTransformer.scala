package Section3OOP

trait MyTransformer[-A, B] {

  def transform(input: A): B

}
