package dwaspada.krl.trip.domain.model

import dwaspada.krl.trip.domain.service.{FakedDistanceFeeCalculator, GateTripChecker}
import dwaspada.krl.trip.infrastructure.persistence.{InMemoryStationRepository, InMemoryTripRepository}

/**
  * To simulate the domain behaviour quickly, make it simple first
  */
object DomainRegistry {
  val tripRepository = new InMemoryTripRepository
  val stationRepository = new InMemoryStationRepository
  val tripChecker = new GateTripChecker
  val distanceFeeCalculator = new FakedDistanceFeeCalculator
}
