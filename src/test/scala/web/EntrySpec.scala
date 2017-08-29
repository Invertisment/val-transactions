package web

import org.scalatest._

/**
  * Created by martin on 26/08/17.
  */
class EntrySpec extends FlatSpec with Matchers {

  "Entry" should "have a default source value" in {
    val out = Entry("John", 5)
    out should equal(Entry(Entry.infiniteMoney, "John", 5))
  }

}
