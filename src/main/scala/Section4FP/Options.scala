package Section4FP

import java.io

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // Unsafe APIs
  def unsafeMethod(): String = null
  //val result = Some(unsafeMethod()) // WRONG because it might evaluate to Some(null)
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // Chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult: Option[String] = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe methods
  def betterUnsafeMethod(): Option[String] = None
  def betterBackUpMethod(): Option[String] = Some("A valid result")

  val betterChainedMethod = betterUnsafeMethod().orElse(betterBackUpMethod())

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - bad practice

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for comprehensions
  // Exercise
  val config: Map[String, String] = Map(
    // Fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect: String = "Connected" // Connect to a server
  }

  object Connection {
    val random: Random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection) else None
    }
  }

  // try to establish a connection, if successful print the connect method
  val host: Option[String] = config.get("host")
  val port: Option[String] = config.get("port")

  def establishConnection(host: String, port: String): Option[String] = {
    Connection(host, port) match {
      case Some(conn) => Some(conn.connect)
      case None => establishConnection(host, port)
    }
  }

  // host.flatMap(h => port.flatMap(p => Connection(h, p))).foreach(println)
  host.flatMap(h => port.flatMap(p => establishConnection(h, p))).foreach(println)

  // Chained calls
  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => establishConnection(h, p)))
    .foreach(println)

  // for comprehension
  for {
    host <- config.get("host")
    port <- config.get("port")
    conn <- establishConnection(host, port)
  } println(conn)

}
