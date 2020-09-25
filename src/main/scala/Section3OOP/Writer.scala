package Section3OOP

class Writer(firstName: String, surname: String, val year: Int) {

  val fullName: String = s"$firstName $surname"

}

class Novel(name: String, yearOfRelease: Int, author: Writer) {

  def authorAge: Int = yearOfRelease - author.year

  def isWrittenBy(author: Writer): Boolean = this.author == author

  def copy(newReleaseYear: Int): Novel = new Novel(name, newReleaseYear, author)

}

class Counter(val count: Int) {

  def inc: Counter = {
    println("Incrementing")
    new Counter(count + 1)
  }

  def dec: Counter = {
    println("Decrementing")
    new Counter(count - 1)
  }

  def inc(amount: Int): Counter = {
    if (amount <= 0) this
    else inc.inc(amount - 1)
  }

  def dec(amount: Int): Counter = {
    if (amount <= 0) this
    else dec.dec(amount - 1)
  }

  def print(): Unit = println(count)

}
