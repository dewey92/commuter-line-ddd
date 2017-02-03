package dwaspada.krl.trip.domain.event

import java.util.Date

import dwaspada.krl.trip.domain.model.{CardId, StationId}
import dwaspada.thedaam.domain.DomainEvent

case class CardTappedIn(cardId: CardId, stationId: StationId) extends DomainEvent {
  val occurredAt = new Date
}
