package dwaspada.thedaam.domain

import scala.collection.mutable

object DomainEventPublisher {
  val domainEvents: mutable.ListBuffer[DomainEvent] = mutable.ListBuffer()
  val domainEventListeners: mutable.Map[String, DomainEventListener] = mutable.Map()

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

    reset()
  }

  def reset(): Unit = {
    domainEvents.clear
  }

  def subscribe(listener: DomainEventListener): Unit = {
    val listenerClass = listener.getClass.getName

    if (! domainEventListeners.isDefinedAt(listenerClass)) {
      domainEventListeners.put(listenerClass, listener)
    }
  }
}
