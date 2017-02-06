package dwaspada.krl.trip.domain.model

import dwaspada.thedaam.domain.ValueObject

class CardId(val id: String) extends ValueObject[CardId] {
  override def sameValueAs(other: CardId): Boolean = id.equals(other.id)
}
