package dwaspada.thedaam.application

sealed trait BaseCommandBus {
  type H <: CommandHandler[Command]
}

object CommandBus extends BaseCommandBus {
  def handle(command: Command): Unit = {
    val handlerName = getHandlerName(command)
    val handlerInstance: H = getClassInstance(handlerName)

    handlerInstance.handle(command)
  }

  def getHandlerName(command: Command): String = {
    val commandClass = command.getClass.getName
    val commandName: String = commandClass.split("Command")(0)

    commandName + "Handler"
  }

  /**
    * Get handler instance of the command
    *
    * @param handler Name of the handler
    * @return
    */
  def getClassInstance(handler: String): H = {
    Class.forName(handler).newInstance.asInstanceOf[H]
  }
}
