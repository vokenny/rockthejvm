package Section4FP

import scala.util.{Failure, Random, Success, Try}

object HandlingFailures extends App {

  // Success
  val aSuccess: Try[Int] = Success(3)
  val aFailure: Try[Nothing] = Failure(new RuntimeException("SUPER FAILURE"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod: String = throw new RuntimeException("NO STRING")

  // Try objects using the apply method
  val potentialFailure: Try[String] = Try(unsafeMethod)
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // Utilities
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  // orElse
  def backupMethod(): String = "A valid string"

  val fallbackTry = Try(unsafeMethod).orElse(Try(backupMethod()))
  println(fallbackTry)

  def betterUnsafeMethod(): Try[String] = Try(throw new RuntimeException("NO STRING"))
  def betterBackupMethod(): Try[String] = Try("A valid string")

  val betterFallbackTry = betterUnsafeMethod().orElse(betterBackupMethod())

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // for comprehensions
  // Exercise
  val host = "localhost"
  val port = "8080"

  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random: Random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url)) match {
      case Success(page) => Success(page)
      case Failure(_) => getSafe(url)
    }
  }

  object HttpService {
    val random: Random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Connection failed")
    }

    def getConnectionSafe(host: String, port: String): Try[Connection] = {
      Try(getConnection(host, port)) match {
        case Success(conn) => Success(conn)
        case Failure(_) => getConnectionSafe(host, port)
      }
    }
  }

  // If you get the html from the connection, print it to the console (render)
  HttpService.getConnectionSafe(host, port).flatMap(conn => conn.getSafe(host + port)).foreach(renderHTML)

  // for comprehension
  for {
    conn <- HttpService.getConnectionSafe(host, port)
    page <- conn.getSafe(host + port)
  } renderHTML(page)

}
