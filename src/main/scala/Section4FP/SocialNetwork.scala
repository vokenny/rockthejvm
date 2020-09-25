package Section4FP

object SocialNetwork extends App {

  var network: Map[Person, List[Person]] = Map.empty
  var testNetwork: Map[Person, List[Person]] = Map(
    Person("Bob") -> List(
      Person("James"),
      Person("Amy"),
      Person("Phil")
    ),
    Person("James") -> List(
      Person("Bob"),
      Person("Amy"),
      Person("Phil")
    ),
    Person("Amy") -> List(
      Person("Bob"),
      Person("James")
    ),
    Person("Phil") -> List(
      Person("Bob"),
      Person("James")
    ),
    Person("Lily") -> List(Person("Amy")),
    Person("Ron") -> List()
  )

  def add(person: Person): Unit = network += (person -> List())

  def remove(person: Person): Unit = {
    person.friendList.foreach { f => f.unfriend(person) }
    network -= person
  }

  def personsWithMostFriends: Seq[Person] = {
    // Only doing maxBy on network will only return one Person, and does not account for ties
    val groupedByNumFriends: Map[Int, Map[Person, List[Person]]] =
      network.groupBy { case (_, v) => v.size }

    val personsWithMostFriends: Map[Person, List[Person]] =
      groupedByNumFriends.maxBy { case (i, _) => i }._2

    personsWithMostFriends.keys.toSeq
  }

  def peopleWithNoFriendsCount: Int = network.count { case (_, friendList) => friendList.isEmpty }

  def hasSocialConnection: Person => Person => Boolean = {
    p1: Person => p2: Person => p1.friendList.intersect(p2.friendList).nonEmpty
  }

  case class Person(name: String) {

    def friendList: List[Person] = network.get(this) match {
      case Some(friendList) => friendList
      case None =>
        add(this)
        this.friendList
    }

    def friend(person: Person): Unit = {
      if (!friendList.contains(person)) {
        network += (this -> (friendList :+ person))
        person.friend(this)
      }
    }

    def unfriend(person: Person): Unit = {
      if (friendList.contains(person)) {
        network += (this -> friendList.filter(_ != person))
        person.unfriend(this)
      }
    }

    def friendsCount: Int = friendList.size

  }
}
