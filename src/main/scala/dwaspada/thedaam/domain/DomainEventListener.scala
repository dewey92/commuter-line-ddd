package dwaspada.thedaam.domain

trait DomainEventListener {
  def isSubscribedTo(domainEvent: DomainEvent): Boolean
  def handleEvent(domainEvent: DomainEvent): Unit
}
