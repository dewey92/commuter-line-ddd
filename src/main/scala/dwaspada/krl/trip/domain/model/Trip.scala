package dwaspada.krl.trip.domain.model

import java.util.Date

case class Trip(cardId: CardId, fromStationId: StationId, dateIn: Date = new Date()) {
  var dateCompleted: Option[Date] = None
  var toStationId: Option[StationId] = None

  def completeTrip(toStation: StationId): Unit = if (! isCompleted) {
    dateCompleted = Some(new Date())
    toStationId = Some(toStation)
  }

  def isCompleted: Boolean = dateCompleted.isDefined && toStationId.isDefined

  def dateOut: Option[Date] = dateCompleted
}
