package dwaspada

import dwaspada.krl.trip.application.tapin.TapInCommand
import dwaspada.krl.trip.application.tapout.TapOutCommand
import dwaspada.krl.trip.domain.model.{CardId, StationId}
import dwaspada.thedaam.application.CommandBus

object Main {
  def main(args: Array[String]): Unit = {
    val tapInCommand = TapInCommand(new StationId("1"), new CardId("1"), 12000)
    CommandBus.handle(tapInCommand)

    val tapOutCommand = TapOutCommand(new StationId("3"), new CardId("1"), 12000)
    CommandBus.handle(tapOutCommand)
  }
}
