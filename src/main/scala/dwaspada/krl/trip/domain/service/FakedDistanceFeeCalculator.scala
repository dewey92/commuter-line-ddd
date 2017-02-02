package dwaspada.krl.trip.domain.service

import dwaspada.krl.trip.domain.model._

class FakedDistanceFeeCalculator extends DistanceFeeCalculator {
  val minimumRequiredDistanceInKM: Double       = 25
  val feeInFirstMinimumDistance: Int            = 4000
  val thresholdAfterMinimumDistanceInKM: Double = 10
  val feeAfterMinimumDistance: Int              = 2000

  def calculate(from: StationId, to: StationId): (Distance, Int) = {
    // TODO: distance calculation
    // For now, let's return a faked distance in kilometers

    if (from sameValueAs to) {
      (Distance(0, LengthUnit("KM")), 2000)
    }

    val distance = Distance(29.8, LengthUnit("KM"))

    (distance, calculateFeeFromDistance(distance))
  }

  /**
    * When distance travelled is less than 25KM, charge Rp4.000
    * and Rp2.000 for each next 10KM
    *
    * @param distance The distance to calculate
    * @return
    */
  def calculateFeeFromDistance(distance: Distance): Int = {
    val distanceInKM = distance.toKilometer

    if (distanceInKM.distance <= minimumRequiredDistanceInKM) {
      feeInFirstMinimumDistance
    } else {
      greaterThanMinimumDistanceCalculator(distanceInKM.distance - minimumRequiredDistanceInKM)
    }
  }

  private def greaterThanMinimumDistanceCalculator(distance: Double): Int = {
    val baseIteration = (distance / thresholdAfterMinimumDistanceInKM).ceil.toInt

    (baseIteration * feeAfterMinimumDistance) + feeInFirstMinimumDistance
  }
}
