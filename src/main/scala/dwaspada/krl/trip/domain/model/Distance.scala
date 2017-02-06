package dwaspada.krl.trip.domain.model

import dwaspada.thedaam.domain.ValueObject

case class Distance(distance: Double, lengthUnit: LengthUnit) extends ValueObject[Distance] {
  require(distance >= 0, "Distance cannot be less than 0")

  def toKilometer: Distance = {
    if (lengthUnit.unit == "M") {
      Distance(distance / 1000, LengthUnit("KM"))
    }

    Distance(distance, LengthUnit("KM"))
  }

  def toMeter: Distance = {
    if (lengthUnit.unit == "KM") {
      Distance(distance * 1000, LengthUnit("M"))
    }

    Distance(distance, LengthUnit("M"))
  }

  override def sameValueAs(other: Distance): Boolean = {
    other.distance == distance &&
    other.lengthUnit.sameValueAs(lengthUnit)
  }
}
