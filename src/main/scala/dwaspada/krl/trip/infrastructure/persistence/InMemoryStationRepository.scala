package dwaspada.krl.trip.infrastructure.persistence

import dwaspada.krl.trip.domain.model.{Station, StationId, StationRepository}
import scala.collection.immutable

class InMemoryStationRepository extends StationRepository {
  val stations = immutable.Map[String, Station](
    "1" -> new Station(new StationId("1")),
    "2" -> new Station(new StationId("2")),
    "3" -> new Station(new StationId("3")),
    "4" -> new Station(new StationId("4"))
  )

  override def findAll: immutable.Map[String, Station] = stations

  override def findById(stationId: StationId):  Option[Station] = stations.get(stationId.id)
}
