package dwaspada.thedaam.application

trait CommandHandler[C <: Command] {
  def handle(command: C): Unit
}
