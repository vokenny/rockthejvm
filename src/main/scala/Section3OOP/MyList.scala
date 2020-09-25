package Section3OOP

abstract class MyList[+T] {

  def head: T
  def tail: MyList[T]
  def isEmpty: Boolean
  def add[B >: T](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def filter(pred: T => Boolean): MyList[T]
  def map[B](tf: T => B): MyList[B]
  def flatMap[B](tf: T => MyList[B]): MyList[B]

  def ++[B >: T](list: MyList[B]): MyList[B]

  // HOFs
  def foreach(f: T => Unit): Unit
  def sort(compare: (T, T) => Int): MyList[T]
  def zipWith[B, C](list: MyList[B], pair: (T, B) => C): MyList[C]
  def fold[B](start: B)(f: (B, T) => B): B

}

case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)
  override def printElements: String = ""

  override def filter(pred: Nothing => Boolean): MyList[Nothing] = Empty
  override def map[B](tf: Nothing => B): MyList[B] = Empty
  override def flatMap[B](tf: Nothing => MyList[B]): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()
  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  override def zipWith[B, C](list: MyList[B], pair: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length") else Empty
  }
  override def fold[B](start: B)(f: (B, Nothing) => B): B = start

}

case class Cons[+T](h: T, t: MyList[T]) extends MyList[T] {

  override def head: T = h
  override def tail: MyList[T] = t
  override def isEmpty: Boolean = false
  override def add[B >: T](element: B): MyList[B] = Cons(element, this)
  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  override def filter(pred: T => Boolean): MyList[T] = {
    if (pred(h)) Cons(h, t.filter(pred))
    else t.filter(pred)
  }

  override def map[B](tf: T => B): MyList[B] = Cons(tf(h), t.map(tf))
  override def flatMap[B](tf: T => MyList[B]): MyList[B] = tf(h) ++ t.flatMap(tf)

  override def ++[B >: T](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  override def foreach(f: T => Unit): Unit = {
    f(h)
    // t.foreach(f)
    for (elems <- t) f(elems)
  }

  override def sort(compare: (T, T) => Int): MyList[T] = {
    def insert(x: T, sortedList: MyList[T]): MyList[T] = {
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (T, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Cons(zip(h, list.head), tail.zipWith(list.tail, zip))
  }

  override def fold[B](start: B)(f: (B, T) => B): B = {
    val newStart: B = f(start, h)
    tail.fold(newStart)(f)
  }

}