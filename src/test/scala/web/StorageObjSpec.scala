package web

import org.scalatest._

/**
  * Created by martin on 21/08/17.
  */
class StorageObjSpec extends FlatSpec with Matchers {

  val rand = new java.util.Random()

  "ConcurrentStorage" should "set default Storage" in {
    val entry = Entry("From_" + rand.nextInt(), "To_" + rand.nextInt(), rand.nextInt())
    val concurrentStorage = ConcurrentStorage(Storage(List(entry)))
    concurrentStorage.reference.get() should equal(Storage(List(entry)))
  }

  "ConcurrentStorage" should "update itself" in {
    val entry = Entry("From_" + rand.nextInt(), "To_" + rand.nextInt(), rand.nextInt())
    val concurrentStorage = ConcurrentStorage()
    concurrentStorage.transfer(entry)
    concurrentStorage.reference.get() should equal(Storage(List(entry)))
  }

  "ConcurrentStorage" should "return new entry value" in {
    val entry = Entry("From_" + rand.nextInt(), "To_" + rand.nextInt(), rand.nextInt())
    val concurrentStorage = ConcurrentStorage()
    val out: Entry = concurrentStorage.transfer(entry)
    out should equal(entry)
  }
}