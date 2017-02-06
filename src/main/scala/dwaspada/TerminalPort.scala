package dwaspada

import dwaspada.krl.shared.eventlistener.{PrintTapInCredentials, PrintTapOutCredentials}

import scala.io.StdIn.readLine
import dwaspada.krl.trip.application.tapin.TapInCommand
import dwaspada.krl.trip.application.tapout.TapOutCommand
import dwaspada.krl.trip.domain.model.{CardId, StationId}
import dwaspada.thedaam.application.CommandBus
import dwaspada.thedaam.domain.DomainEventPublisher

import scala.collection.mutable

/**
  * Simple port for terminal. Not a fancy one, but enough to
  * show how our application can be accessed by many interfaces
  */
object TerminalPort {
  def main(args: Array[String]): Unit = {
    // Register all domain event listeners first
    DomainEventPublisher.subscribe(new PrintTapInCredentials)
    DomainEventPublisher.subscribe(new PrintTapOutCredentials)

    listenToInput()
  }

  def listenToInput(): Unit = {
    val commandLine = readLine("Command: ")
    val commandArray = commandLine.split(" ")

    val command: String = commandArray.headOption.getOrElse {
      throw new RuntimeException("No available command! Should be one of exit, quit, tapin, and tapout")
    }
    val params: mutable.Map[String, String] = mutable.Map[String, String]()

    if (command == "exit" || command == "quit") System.exit(1)

    commandArray.tail.foreach { param =>
      val Array(key: String, value: String) = param.split("=")

      key match {
        case "station" => params += "station" -> value.trim
        case "card"    => params += "cardId" -> value.trim
        case "credit"  => params += "cardCredit" -> value.trim
        case _         => throw new Exception("INVALID INPUT")
      }
    }

    command match {
      case "tapin"  => CommandBus.handle(
        TapInCommand(new StationId(params("station")), new CardId(params("cardId")), params("cardCredit").toInt)
      )
      case "tapout" => CommandBus.handle(
        TapOutCommand(new StationId(params("station")), new CardId(params("cardId")), params("cardCredit").toInt)
      )
      case _        => throw new Exception("INVALID COMMAND")
    }

    listenToInput()
  }
}
