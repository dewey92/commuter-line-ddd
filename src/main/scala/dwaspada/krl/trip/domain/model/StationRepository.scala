package dwaspada.krl.trip.domain.model

trait StationRepository {
  def findAll: Map[String, Station]
  def findById(stationId: StationId): Option[Station]
}
