package dwaspada

import scala.io.StdIn.readLine
import dwaspada.krl.trip.application.tapin.TapInCommand
import dwaspada.krl.trip.application.tapout.TapOutCommand
import dwaspada.krl.trip.domain.model.{CardId, StationId}
import dwaspada.thedaam.application.CommandBus

/**
  * Simple port for terminal. Not a fancy one, but enough to
  * show how our application can be accessed by many interfaces
  */
object TerminalPort {
  def main(args: Array[String]): Unit = {
    val commandLine = readLine("Command: ")
    val commandArray = commandLine.split(" ")

    val command: String = commandArray.head
    var station: String = ""
    var cardId: String  = ""
    var cardCredit: Int = 0

    if (command == "exit" || command == "quit") return

    commandArray.tail.foreach { param =>
      val Array(key: String, value: String) = param.split("=")

      key match {
        case "station" => station = value.trim
        case "card"    => cardId = value.trim
        case "credit"  => cardCredit = value.toInt
        case _         => throw new Exception("INVALID INPUT")
      }
    }

    command match {
      case "tapin"  => CommandBus.handle(TapInCommand(new StationId(station), new CardId(cardId), cardCredit))
      case "taoput" => CommandBus.handle(TapOutCommand(new StationId(station), new CardId(cardId), cardCredit))
      case _        => throw new Exception("INVALID COMMAND")
    }

    main(args)
  }
}
