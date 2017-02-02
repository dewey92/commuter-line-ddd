package dwaspada.trip.domain.model

import dwaspada.krl.trip.domain.exception.{CannotTapInException, CannotTapOutException}
import dwaspada.krl.trip.domain.model._
import dwaspada.krl.trip.domain.service.GateTripChecker
import org.scalatest._
import org.scalamock.scalatest.MockFactory

class StationSpec extends FlatSpec with MockFactory {
  info("As a passenger")
  info("I want to tap in with my card to be able to enter the platform")
  info("And tap out with the same card to leave the platform")
  info("So I can make a full trip with Commuter Line")

  val card = Card(new CardId("1"), credit = 70000)
  val station = new Station(new StationId("2"), "Manggarai")

  it should "reject a card with credit less than Rp.12,000 when tapping in" in {
    assertThrows[CannotTapInException] {
      station.gateIn(Card(new CardId("1"), credit = 10000), new GateTripChecker)
    }
  }

  it should "reject a registered (already tapped in) card  when tapping in" in {
    val tc = stub[TripChecker]
    tc.isCardAlreadyTappedIn _ when card returns true

    assertThrows[CannotTapInException] {
      station.gateIn(card, tc)
    }
  }

  it should "reject a non-registered card when tapping out" in {
    val tc = stub[TripChecker]
    val dc = stub[DistanceFeeCalculator]
    // val trip = Trip(new CardId("1"), new StationId("2"))
    tc.getTripByCard _ when card returns None

    assertThrows[CannotTapOutException] {
      station.gateOut(card, tc, dc)
    }
  }
}
