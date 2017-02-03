package dwaspada.krl.trip.domain.event

import java.util.Date

import dwaspada.krl.trip.domain.model.{Card, Distance, StationId}
import dwaspada.thedaam.domain.DomainEvent

case class CardTappedOut(card: Card, stationId: StationId, distance: Distance, totalFee: Int) extends DomainEvent {
  val occurredAt = new Date()
}
