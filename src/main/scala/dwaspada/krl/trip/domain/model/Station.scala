package dwaspada.krl.trip.domain.model

import dwaspada.krl.trip.domain.event.{CardTappedIn, CardTappedOut}
import dwaspada.krl.trip.domain.exception.{CannotTapInException, CannotTapOutException}
import dwaspada.thedaam.domain.{AggregateRoot, DomainEventPublisher}

object Station {
  def apply(id: StationId, name: String): Station = {
    require(name.trim.length < 1, "Station name must not be empty")

    new Station(id, name)
  }
}

class Station(val id: StationId, val name: String) extends AggregateRoot {
  val minimumCredit: Int = 12000

  def gateIn(card: Card, tripChecker: TripChecker): Unit = {
    if (tripChecker.isCardAlreadyTappedIn(card)) {
      throw new CannotTapInException("Must tap out first")
    }

    if (card.credit < minimumCredit) {
      throw new CannotTapInException(s"Card credit is less than Rp. $minimumCredit")
    }

    // Raise event passenger has tapped in
    DomainEventPublisher.raise(CardTappedIn(card.id, id))
  }

  def gateOut(card: Card, tripChecker: TripChecker, distanceFeeCalculator: DistanceFeeCalculator): Unit = {
    val trip: Trip = tripChecker.getTripByCard(card) match {
      case Some(trip: Trip) => trip
      case None => throw new CannotTapOutException("Card is not registered in the trip")
    }

    val (distance: Distance, totalFee: Int) = distanceFeeCalculator.calculate(trip.fromStationId, id)

    val subtractedCard = card.subtractCredit(totalFee)

    trip.complete(id)

    // Raise event passenger has tapped out
    DomainEventPublisher.raise(CardTappedOut(subtractedCard, id, distance, totalFee))
  }
}
