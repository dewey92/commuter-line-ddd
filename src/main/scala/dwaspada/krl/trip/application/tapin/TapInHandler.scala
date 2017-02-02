package dwaspada.krl.trip.application.tapin

import dwaspada.krl.trip.domain.exception.CannotTapInException
import dwaspada.krl.trip.domain.model.{Card, DomainRegistry, Trip}
import dwaspada.thedaam.application.CommandHandler

class TapInHandler extends CommandHandler[TapInCommand] {
  override def handle(command: TapInCommand): Unit = {
    val station = DomainRegistry.stationRepository.findOrFailById(command.stationId)
    val card = new Card(command.cardId, command.credit)

    try {
      station.gateIn(card, DomainRegistry.tripChecker)

      val trip = Trip(command.cardId, command.stationId)
      DomainRegistry.tripRepository.add(trip)

      println(s"From Station: ${station.name}\n")
    } catch {
      case e: CannotTapInException => println(e.getMessage)
      case _: Throwable => println("Somehow cannot proceed. Noo :(")
    }
  }
}
