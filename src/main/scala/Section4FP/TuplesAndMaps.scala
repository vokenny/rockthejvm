package Section4FP

object TuplesAndMaps extends App {

  // Tuples = finite "ordered" lists
  val aTuple: (Int, String) = new Tuple2(2, "Hello Scala") // Tuples2[Int, String] = (Int, String)
  val anotherTuple: (Int, String) = (1, "Hello World")

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "Goodbye Java"))
  println(aTuple.swap) // ("Hello Scala", 2)

  // Maps are key -> value
  val aMap: Map[String, Int] = Map()

  val phonebook: Map[String, Int] = Map("Jim" -> 101, "Bob" -> 102).withDefaultValue(-1)
  println(phonebook)

  // Map operations
  println(phonebook.contains("JIm"))
  println(phonebook("Bob"))

  // Add pairing
  val newPhonebook = phonebook + ("Mary" -> 103)
  println(newPhonebook)

  // functionals on maps
  // map, filter, flatMap
  println(phonebook.map { case (k, v) => k.toLowerCase -> v })

  // filterKeys
  println(phonebook.filterKeys(_.startsWith("J")))

  // mapValues
  println(phonebook.mapValues(value => value * 10))
  println(phonebook.mapValues(value => "0808" + value))

  // Conversion to other collections
  println(phonebook.toList)
  println(List(("Kenny", 208)).toMap)

  val names = List("Jenny", "Jake", "Kenny", "Andrew", "Daniel", "Kevin")
  println(names.groupBy(name => name.charAt(0)))

  val bPhonebook = Map("Jim" -> 100, "JIM" -> 101)
  val cPhonebook = bPhonebook.map{ case (k, v) => k.toLowerCase -> v}
  println(cPhonebook)

}
