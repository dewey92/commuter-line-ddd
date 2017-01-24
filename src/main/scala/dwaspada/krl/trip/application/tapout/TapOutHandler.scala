package dwaspada.krl.trip.application.tapout

import dwaspada.krl.trip.domain.exception.CannotTapOutException
import dwaspada.krl.trip.domain.model._
import dwaspada.krl.trip.domain.service.GateOutService
import dwaspada.thedaam.application.CommandHandler

class TapOutHandler extends CommandHandler[TapOutCommand] {
  override def handle(command: TapOutCommand): Unit = {
    val station = DomainRegistry.stationRepository.findById(command.stationId)
    if (station.isEmpty) {
      throw new CannotTapOutException("Invalid Station")
    }

    val card = new Card(command.cardId, command.credit)
    val gateOutService = new GateOutService(station.get, card)

    try {
      gateOutService.gateOut()

      val trip = Trip(command.cardId, command.stationId)
      DomainRegistry.tripRepository.remove(trip)

      println(s"\nTo Station: ${station.get.name}")
    } catch {
      case e: CannotTapOutException => println(e.getMessage)
      case _: Throwable => println("Somehow cannot proceed. Noo :(")
    }
  }
}
