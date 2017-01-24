package dwaspada.krl.trip.application.tapin

import dwaspada.krl.trip.domain.exception.CannotTapInException
import dwaspada.krl.trip.domain.model.{Card, DomainRegistry, Trip}
import dwaspada.krl.trip.domain.service.GateInService
import dwaspada.thedaam.application.CommandHandler

class TapInHandler extends CommandHandler[TapInCommand] {
  override def handle(command: TapInCommand): Unit = {
    val station = DomainRegistry.stationRepository.findById(command.stationId)
    if (station.isEmpty) {
      throw new CannotTapInException("Invalid Station")
    }

    val card = new Card(command.cardId, command.credit)
    val gateInService = new GateInService(card, station.get)

    try {
      gateInService.gateIn()

      val trip = Trip(command.cardId, command.stationId)
      DomainRegistry.tripRepository.add(trip)

      println(s"From Station: ${station.get.name}\n")
    } catch {
      case e: CannotTapInException => println(e.getMessage)
      case _: Throwable => println("Somehow cannot proceed. Noo :(")
    }
  }
}
