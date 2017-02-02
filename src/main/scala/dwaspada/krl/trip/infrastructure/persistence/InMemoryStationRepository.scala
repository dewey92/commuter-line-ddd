package dwaspada.krl.trip.infrastructure.persistence

import dwaspada.krl.trip.domain.exception.InvalidStationException
import dwaspada.krl.trip.domain.model.{Station, StationId, StationRepository}

import scala.collection.immutable

class InMemoryStationRepository extends StationRepository {
  val stations = immutable.Map[String, Station](
    "1" -> new Station(new StationId("1"), "Tangerang"),
    "2" -> new Station(new StationId("2"), "Manggarai"),
    "3" -> new Station(new StationId("3"), "Depok"),
    "4" -> new Station(new StationId("4"), "Bogor")
  )

  override def findAll: immutable.Map[String, Station] = stations

  override def findById(stationId: StationId):  Option[Station] = stations.get(stationId.id)

  override def findOrFailById(stationId: StationId): Station = {
    findById(stationId) match {
      case Some(station: Station) => station
      case None => throw new InvalidStationException("Invalid Station")
    }
  }
}
