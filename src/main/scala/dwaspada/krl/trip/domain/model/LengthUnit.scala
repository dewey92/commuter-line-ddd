package dwaspada.krl.trip.domain.model

import dwaspada.thedaam.domain.ValueObject

case class LengthUnit(unit: String) extends ValueObject[LengthUnit] {
  val validUnits = List("KM", "M")
  require(validUnits.contains(unit), "Invalid length unit")

  override def sameValueAs(other: LengthUnit): Boolean = {
    other != null && other.unit == unit
  }
}
