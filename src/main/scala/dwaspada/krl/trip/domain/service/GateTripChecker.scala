package dwaspada.krl.trip.domain.service

import dwaspada.krl.trip.domain.model.{Card, DomainRegistry, Trip, TripChecker}

class GateTripChecker extends TripChecker {
  override def isCardAlreadyTappedIn(card: Card): Boolean = {
    DomainRegistry.tripRepository.findByCard(card.id) match {
      case Some(_: Trip) => true
      case None => false
    }
  }

  override def getTripByCard(card: Card): Option[Trip] = {
    DomainRegistry.tripRepository.findByCard(card.id)
  }
}
