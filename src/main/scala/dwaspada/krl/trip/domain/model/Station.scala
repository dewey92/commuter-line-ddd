package dwaspada.krl.trip.domain.model

import dwaspada.krl.trip.domain.exception.CannotTapInException

object Station {
  def apply(id: StationId): Station = {
    require(id != null, "Station ID must not be null")

    new Station(id)
  }
}

class Station(id: StationId) {
  val minimumCredit: Int = 12000

  def gateIn(card: Card): Unit = {
    if (card.credit < minimumCredit) {
      throw new CannotTapInException(s"Card credit is less than Rp. $minimumCredit")
    }

    // Raise event passenger has tapped in
  }

  def gateOut(card: Card, trip: Trip): Unit = {
    val distanceAndFee: (Distance, Int) = DomainRegistry.distanceFeeCalculator.calculate(trip.fromStationId, id)
    val totalFee = distanceAndFee._2

    card.subtractCredit(totalFee)

    trip.completeTrip(id)

    // Raise event passenger has tapped out
  }
}
