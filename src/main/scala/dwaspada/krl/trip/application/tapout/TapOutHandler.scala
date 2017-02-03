package dwaspada.krl.trip.application.tapout

import dwaspada.krl.trip.domain.exception.CannotTapOutException
import dwaspada.krl.trip.domain.model._
import dwaspada.thedaam.application.CommandHandler
import dwaspada.thedaam.domain.DomainEventPublisher

class TapOutHandler extends CommandHandler[TapOutCommand] {
  override def handle(command: TapOutCommand): Unit = {
    val station = DomainRegistry.stationRepository.findOrFailById(command.stationId)
    val card = Card(command.cardId, command.credit)

    try {
      station.gateOut(card, DomainRegistry.tripChecker, DomainRegistry.distanceFeeCalculator)

      DomainRegistry.tripRepository.remove(Trip(command.cardId, command.stationId))

      // Publish events
      DomainEventPublisher.publish()
    } catch {
      case e: CannotTapOutException => println(e.getMessage)
      case _: Throwable => println("Somehow cannot proceed. Noo :(")
    }
  }
}
