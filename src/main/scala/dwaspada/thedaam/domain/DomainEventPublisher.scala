package dwaspada.thedaam.domain

object DomainEventPublisher {
  val domainEvents: List[DomainEvent] = List()

  def publish(): Unit = {
    domainEvents.foreach(println)
  }
}
