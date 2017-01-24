package dwaspada.krl.trip.domain.model

trait DistanceFeeCalculator {
  def calculate(from: StationId, to: StationId): (Distance, Int)
}
