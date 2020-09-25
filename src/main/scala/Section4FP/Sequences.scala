package Section4FP
import scala.collection.immutable
import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence: Seq[Int] = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7, 5, 6))
  println(aSequence.sorted)

  // Range
  val aRange: Seq[Int] = 1 to 10
  val anotherRange: Seq[Int] = 1 until 10
  aRange.foreach(println)
  anotherRange.foreach(println)

  (1 to 10).foreach(x => println(x + 2))

  // Lists
  val aList: List[Int] = List(1, 2, 3, 4)
  val prepended: List[Int] = 42 :: aList
  val altPrependAppend: List[Int] = 0 +: aList :+ 5
  println(prepended)
  println(altPrependAppend)
  println(aList :: 5 :: Nil) // List[Any] = List(List(1, 2, 3, 4), 5)
  println(aList ::: 5 :: Nil) // List[Int] = List(1, 2, 3, 4, 5)

  val fiveApples = List.fill(5)("apple")
  println(fiveApples)

  println(aList.mkString("-|-"))

  // Arrays
  val numbers: Array[Int] = Array(1, 2, 3, 4)
  val threeElems: Array[String] = Array.ofDim[String](3)
  threeElems.foreach(println)

  // mutation
  numbers(2) = 0
  println(numbers.mkString(" "))

  // array & seq

  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // Vectors vs Lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(coll: Seq[Int]): Double = {
    val r: Random.type = Random
    val times: Seq[Long] = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      coll.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList: List[Int] = (1 to maxCapacity).toList
  val numbersVector: Vector[Int] = (1 to maxCapacity).toVector

  // Keeps reference details -- better for small sizes
  // Updating in the middle takes too long
  println(getWriteTime(numbersList))
  // Depth of the tree is small - better for large sizes
  // Needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

}
