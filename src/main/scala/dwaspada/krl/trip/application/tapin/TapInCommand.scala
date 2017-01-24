package dwaspada.krl.trip.application.tapin

import dwaspada.krl.trip.domain.model.{CardId, StationId}
import dwaspada.thedaam.application.Command

case class TapInCommand(stationId: StationId, cardId: CardId, credit: Int = 0) extends Command
