package dwaspada.krl.trip.domain.model

trait TripChecker {
  def isCardAlreadyTappedIn(card: Card): Boolean
  def getTripByCard(card: Card): Option[Trip]
}
