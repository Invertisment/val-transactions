package web

import org.scalatest._

/**
  * Created by martin on 21/08/17.
  */
class StorageSpec extends FlatSpec with Matchers {

  "Storage" should "return given data" in {
    val storage = Storage(List())
    val out = storage
      .transfer(Entry("Donald duck", "Gyro Gearloose", 2))
      .transfer(Entry("Donald duck", "Uncle Scrooge", 5))
    out should equal(
      Storage(List(
        Entry("Donald duck", "Uncle Scrooge", 5),
        Entry("Donald duck", "Gyro Gearloose", 2)
      )))
  }

  "Storage" should "return given data 2" in {
    val storage = Storage(List())
    val out = storage
      .transfer(Entry("Uncle Scrooge", "Huey", 2))
      .transfer(Entry("Uncle Scrooge", "Dewey", 5))
      .transfer(Entry("Uncle Scrooge", "Louie", 1))
    out should equal(
      Storage(List(
        Entry("Uncle Scrooge", "Louie", 1),
        Entry("Uncle Scrooge", "Dewey", 5),
        Entry("Uncle Scrooge", "Huey", 2)
      )))
  }

  "Storage" should "return not destroy elements from constructor" in {
    val storage = Storage(List(Entry("Uncle Scrooge", "Huey", 0)))
    val out = storage
      .transfer(Entry("Uncle Scrooge", "Huey", 2))
      .transfer(Entry("Uncle Scrooge", "Dewey", 5))
      .transfer(Entry("Uncle Scrooge", "Louie", 1))
    out should equal(
      Storage(List(
        Entry("Uncle Scrooge", "Louie", 1),
        Entry("Uncle Scrooge", "Dewey", 5),
        Entry("Uncle Scrooge", "Huey", 2),
        Entry("Uncle Scrooge", "Huey", 0)
      )))
  }

  "Storage.find" should "match to field" in {
    val storage = Storage(List(
      Entry("Donald duck", "Uncle Scrooge", 1),
      Entry("Uncle Scrooge", "Goldie O'Gilt", 1)
    ))
    storage.find("Donald duck") should equal(
      List(Entry("Donald duck", "Uncle Scrooge", 1))
    )
  }

  "Storage.find" should "match from field" in {
    val storage = Storage(List(
      Entry("Donald duck", "Uncle Scrooge", 1),
      Entry("Uncle Scrooge", "Goldie O'Gilt", 2)
    ))
    storage.find("Goldie O'Gilt") should equal(
      List(Entry("Uncle Scrooge", "Goldie O'Gilt", 2))
    )
  }

}