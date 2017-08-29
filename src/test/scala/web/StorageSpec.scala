package web

import org.scalatest._

/**
  * Created by martin on 21/08/17.
  */
class StorageSpec extends FlatSpec with Matchers {

  "Storage" should "return given data" in {
    val storage = Storage(List())
    val out = storage
      .transfer(Entry("John", 2))
      .transfer(Entry("Peter", 5))
    out should equal(
      Storage(List(
        Entry("Peter", 5),
        Entry("John", 2)
      )))
  }

  "Storage" should "return given data 2" in {
    val storage = Storage(List())
    val out = storage
      .transfer(Entry("John", 2))
      .transfer(Entry("Peter", 5))
      .transfer(Entry("Gary", 1))
    out should equal(
      Storage(List(
        Entry("Gary", 1),
        Entry("Peter", 5),
        Entry("John", 2)
      )))
  }

  "Storage" should "return not destroy elements from constructor" in {
    val storage = Storage(List(Entry("Peter", 0)))
    val out = storage
      .transfer(Entry("John", 2))
      .transfer(Entry("Peter", 5))
      .transfer(Entry("Gary", 1))
    out should equal(
      Storage(List(
        Entry("Gary", 1),
        Entry("Peter", 5),
        Entry("John", 2),
        Entry("Peter", 0)
      )))
  }

  "Storage" should "sum all values" in {
    val storage = Storage(List(Entry("Donald duck", 1), Entry("Donald duck", 1)))
    storage.sum() should equal(
      Map[String, BigDecimal](
        "Uncle Scrooge" -> -2,
        "Donald duck" -> 2
      ))
  }

}