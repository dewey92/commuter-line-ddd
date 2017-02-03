package dwaspada.thedaam.domain

import java.util.Date

trait DomainEvent {
  def occurredAt(): Date
}
