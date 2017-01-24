package dwaspada.krl.trip.domain.model

import dwaspada.thedaam.domain.ValueObject

case class Distance(distance: Double, lengthUnit: LengthUnit) extends ValueObject[Distance] {
  require(distance >= 0, "Distance cannot be less than 0")

  def toKilometer: Distance = {
    if (lengthUnit.unit == "M") {
      new Distance(distance / 1000, new LengthUnit("KM"))
    }

    new Distance(distance, new LengthUnit("KM"))
  }

  def toMeter: Distance = {
    if (lengthUnit.unit == "KM") {
      new Distance(distance * 1000, new LengthUnit("M"))
    }

    new Distance(distance, new LengthUnit("M"))
  }

  override def sameValueAs(other: Distance): Boolean = {
    other != null &&
    other.distance == distance &&
    other.lengthUnit.sameValueAs(lengthUnit)
  }
}
