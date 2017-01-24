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
    * @see http://3x14159265.tumblr.com/post/57543163324/playing-with-scala-reflection
    *
    * @param handler Name of the handler
    * @return
    */
  def getClassInstance(handler: String): H = {
    Class.forName(handler).newInstance.asInstanceOf[H]
  }
}
