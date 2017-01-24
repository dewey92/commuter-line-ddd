package dwaspada.thedaam.domain

import java.io.Serializable

trait ValueObject[V] extends Serializable {

  /**
    * Comparable to the same ValueObject
    *
    * @param other concrete type of ValueObject also
    * @return
    */
  def sameValueAs(other: V): Boolean
}
