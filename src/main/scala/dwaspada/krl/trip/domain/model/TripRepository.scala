package dwaspada.krl.trip.domain.model

trait TripRepository {
  def add(trip: Trip): Unit
  def findByCard(id: CardId): Option[Trip]
  def remove(trip: Trip): Unit
}
