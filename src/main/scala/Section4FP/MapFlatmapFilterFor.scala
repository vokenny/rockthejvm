package Section4FP

object MapFlatmapFilterFore extends App {

  val list = 1 :: 2 :: 3 :: Nil

  // map
  println(list.map(_ + 2))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair: Int => List[Int] = x => List(x, x + 1)
  println(list.flatMap(toPair))

  // Print all combinations between two lists
  val chars = List('a', 'b', 'c', 'd')
  val ints = List(1, 2, 3, 4)
  val colours = List("white", "black")

  // "iterations"
  val combinations = ints.filter(_ % 2 == 0).flatMap(i => chars.flatMap( c => colours.map(co => "" + i + c + "-" + co)))
  println(combinations)

  // foreach
  list.foreach(println)

  // for
  val combos = for {
    char <- chars
    int <- ints if int % 2 == 0 // if guard for filtering
    colour <- colours
  } yield char.toString + int + "-" + colour

  println(combos)

  for {
    int <- ints
  } println

  // syntax overload
  list.map { x =>
    x * 2
  }
}
