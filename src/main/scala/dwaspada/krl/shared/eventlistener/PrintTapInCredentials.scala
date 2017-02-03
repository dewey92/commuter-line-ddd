package dwaspada.krl.shared.eventlistener

import dwaspada.krl.trip.domain.event.CardTappedIn
import dwaspada.krl.trip.domain.model.DomainRegistry
import dwaspada.thedaam.domain.{DomainEvent, DomainEventListener}

class PrintTapInCredentials extends DomainEventListener {
  override def isSubscribedTo(domainEvent: DomainEvent): Boolean = {
    domainEvent.getClass.getName == CardTappedIn.getClass.getName.replace("$", "")
  }

  override def handleEvent(domainEvent: DomainEvent): Unit = {
    val station = DomainRegistry.stationRepository.findOrFailById(domainEvent.asInstanceOf[CardTappedIn].stationId)

    println(s"From Station: ${station.name}\n")
  }
}
