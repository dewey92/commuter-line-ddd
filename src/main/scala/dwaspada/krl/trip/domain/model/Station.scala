package dwaspada.krl.trip.domain.model

import dwaspada.krl.trip.domain.exception.CannotTapInException
import dwaspada.thedaam.domain.AggregateRoot

object Station {
  def apply(id: StationId, name: String): Station = {
    require(id != null, "Station ID must not be null")
    require(name != null && name.trim.length < 1, "Station name must not be empty")

    new Station(id, name)
  }
}

class Station(val id: StationId, val name: String) extends AggregateRoot {
  val minimumCredit: Int = 12000

  def gateIn(card: Card): Unit = {
    if (card.credit < minimumCredit) {
      throw new CannotTapInException(s"Card credit is less than Rp. $minimumCredit")
    }

    // Raise event passenger has tapped in
  }

  def gateOut(card: Card, trip: Trip): Unit = {
    val (distance: Distance, totalFee: Int) = DomainRegistry.distanceFeeCalculator.calculate(trip.fromStationId, id)
    val beforeCredit = card.credit

    card.subtractCredit(totalFee)

    trip.completeTrip(id)

    println(s"TOTAL FEE: Rp.$totalFee with a distance of ${distance.distance}${distance.lengthUnit.unit}")
    println(s"Your credit is subtracted. Rp.$beforeCredit - Rp.$totalFee = Rp.${card.credit}")

    // Raise event passenger has tapped out
  }
}
