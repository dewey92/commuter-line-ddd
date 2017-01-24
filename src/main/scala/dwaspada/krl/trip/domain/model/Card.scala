package dwaspada.krl.trip.domain.model

object Card {
  def apply(id: CardId, credit: Int = 0): Card = {
    require(id != null, "Card ID must not be null")

    new Card(id, credit)
  }
}

class Card(val id: CardId, var credit: Int = 0) {
  addCredit(credit)

  def addCredit(nominal: Int): Unit = {
    if (nominal < 0) {
      throw new Exception("Nominal cannot be less than 0")
    }

    credit += nominal

    // After credit addition, we can raise an event
  }

  def subtractCredit(nominal: Int): Unit = {
    if (nominal < 0) {
      throw new Exception("Nominal cannot be less than 0")
    }

    credit -= nominal

    // After credit subtraction, we can raise an event
  }
}
