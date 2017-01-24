package dwaspada.krl.trip.domain.service

import dwaspada.krl.trip.domain.exception.CannotTapOutException
import dwaspada.krl.trip.domain.model._

class GateOutService(station: Station, card: Card) {
  def gateOut(): Unit = {
    DomainRegistry.tripRepository.findByCard(card.id) match {
      case Some(trip: Trip) => station.gateOut(card, trip)
      case None => throw new CannotTapOutException("Card is not registered in the trip")
    }
  }
}
