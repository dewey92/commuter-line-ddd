package dwaspada.krl.trip.domain.service

import dwaspada.krl.trip.domain.exception.CannotTapInException
import dwaspada.krl.trip.domain.model._

class GateInService(card: Card, station: Station) {
  def gateIn(): Unit = {
    DomainRegistry.tripRepository.findByCard(card.id) match {
      case Some(_: Trip) => throw new CannotTapInException("Must tap out first")
      case None => station.gateIn(card)
    }
  }
}
