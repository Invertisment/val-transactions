package web

import org.scalatest._

/**
  * Created by martin on 21/08/17.
  */
class StorageObjSpec extends FlatSpec with Matchers {

  "StorageObj" should "update itself" in {
    Storage.reference.reset(Storage(List()))
    Storage.transfer(Entry("From", "To", 123))
    Storage.reference.get() should equal(
      Storage(List(
        Entry("From", "To", 123)
      )))
  }

  "StorageObj" should "return new entry value" in {
    Storage.reference.reset(Storage(List()))
    val out: Entry = Storage.transfer(Entry("From", "To", 123))
    out should equal(Entry("From", "To", 123))
  }
}