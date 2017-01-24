package dwaspada.krl.trip.domain.model

import dwaspada.thedaam.domain.ValueObject

class StationId(val id: String) extends ValueObject[StationId] {
  override def sameValueAs(other: StationId): Boolean = {
    other != null && id.equals(other.id)
  }
}
