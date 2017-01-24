package dwaspada.krl.trip.infrastructure.persistence

import dwaspada.krl.trip.domain.model.{CardId, Trip, TripRepository}
import scala.collection.mutable

class InMemoryTripRepository extends TripRepository {
  val trips = mutable.Map[String, Trip]()

  override def add(trip: Trip): Unit = {
    trips += trip.cardId.id -> trip
  }

  override def findByCard(cardId: CardId): Option[Trip] = {
    trips.get(cardId.id)
  }

  override def remove(trip: Trip): Unit = {
    trips -= trip.cardId.id
  }
}
