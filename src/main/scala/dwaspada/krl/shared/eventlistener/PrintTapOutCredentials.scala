package dwaspada.krl.shared.eventlistener

import dwaspada.krl.trip.domain.event.CardTappedOut
import dwaspada.krl.trip.domain.model.DomainRegistry
import dwaspada.thedaam.domain.{DomainEvent, DomainEventListener}

class PrintTapOutCredentials extends DomainEventListener {
  override def isSubscribedTo(domainEvent: DomainEvent): Boolean = {
    domainEvent.getClass.getName == CardTappedOut.getClass.getName.replace("$", "")
  }

  override def handleEvent(domainEvent: DomainEvent): Unit = {
    val ev = domainEvent.asInstanceOf[CardTappedOut]
    val station = DomainRegistry.stationRepository.findOrFailById(ev.stationId)

    val beforeCredit = ev.totalFee + ev.card.credit

    println(s"\nTo Station: ${station.name}\n")
    println(s"TOTAL FEE: Rp.${ev.totalFee} with a distance of ${ev.distance.distance}${ev.distance.lengthUnit.unit}")
    println(s"Your credit is subtracted. Rp.$beforeCredit - Rp.${ev.totalFee} = Rp.${ev.card.credit}")
  }
}
