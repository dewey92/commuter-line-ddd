package dwaspada.krl.trip.domain.model

import dwaspada.thedaam.domain.ValueObject

case class Card(id: CardId, credit: Int = 0) extends ValueObject[Card] {
  require(credit >= 0, "Card credit cannot be less than 0")

  def addCredit(nominal: Int): Card = {
    if (nominal < 0) {
      throw new Exception("Nominal cannot be less than 0")
    }

    Card(id, credit + nominal)
  }

  def subtractCredit(nominal: Int): Card = {
    if (nominal < 0) {
      throw new Exception("Nominal cannot be less than 0")
    }

    Card(id, credit - nominal)
  }

  override def sameValueAs(other: Card): Boolean = {
    (other != null) && other.id == id && other.credit == credit
  }
}
