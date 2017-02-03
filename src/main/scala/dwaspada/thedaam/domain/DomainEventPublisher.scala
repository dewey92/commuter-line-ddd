package dwaspada.thedaam.domain

import scala.collection.mutable.ListBuffer

object DomainEventPublisher {
  var domainEvents: ListBuffer[DomainEvent] = ListBuffer()
  var domainEventListeners: Map[String, DomainEventListener] = Map()

  def raise(domainEvent: DomainEvent): Unit = {
    domainEvents append domainEvent
  }

  def publish(): Unit = {
    for (
      domainEvent <- domainEvents;
      (_, eventListener) <- domainEventListeners
      if eventListener.isSubscribedTo(domainEvent)
    ) {
      eventListener.handleEvent(domainEvent)
    }
  }

  def reset(): Unit = {
    domainEvents = ListBuffer[DomainEvent]()
  }

  def subscribe(listener: DomainEventListener): Unit = {
    val listenerClass = listener.getClass.getName

    if (! domainEventListeners.isDefinedAt(listenerClass)) {
      domainEventListeners += listenerClass -> listener
    }
  }
}
