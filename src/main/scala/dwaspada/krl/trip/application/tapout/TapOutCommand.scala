package dwaspada.krl.trip.application.tapout

import dwaspada.krl.trip.domain.model.{CardId, StationId}
import dwaspada.thedaam.application.Command

case class TapOutCommand(stationId: StationId, cardId: CardId, credit: Int = 0) extends Command
