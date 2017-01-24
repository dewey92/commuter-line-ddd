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
    // Distance should be in Value Object, I know
    val distance = new Distance(24.8, new LengthUnit("KM"))

    (distance, calculateFeeFromDistance(distance))
  }

  /**
    * When distance travelled is less than 25KM, charge Rp4.000
    * and Rp2.000 for each next 10KM
    *
    * @param distance
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

    baseIteration * feeAfterMinimumDistance
  }
}
