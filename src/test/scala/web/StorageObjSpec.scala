package web

import org.scalatest._
import util.TestEntryFactory

/**
  * Created by martin on 21/08/17.
  */
class StorageObjSpec extends FlatSpec with Matchers with TestEntryFactory {

  "ConcurrentStorage" should "set default Storage" in {
    val entry = makeTransaction
    val concurrentStorage = ConcurrentStorage(Storage(Map(), List(entry)))
    concurrentStorage.reference.get() should equal(Storage(Map(), List(entry)))
  }

  "ConcurrentStorage" should "update itself" in {
    val entry = makeTransaction
    val concurrentStorage = ConcurrentStorage()
    concurrentStorage.transfer(entry)
    concurrentStorage.reference.get() should equal(Storage(Map(), List(entry)))
  }

  "ConcurrentStorage" should "return new entry value" in {
    val entry = makeTransaction
    val concurrentStorage = ConcurrentStorage()
    val out: Transaction = concurrentStorage.transfer(entry)
    out should equal(entry)
  }

}