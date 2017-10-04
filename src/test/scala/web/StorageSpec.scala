package web

import java.time.ZonedDateTime

import org.scalatest._

/**
  * Created by martin on 21/08/17.
  */
class StorageSpec extends FlatSpec with Matchers {

  "Storage" should "return given data" in {
    val storage = Storage(Map(), List())
    val transaction1 = Transaction("Donald duck", "Gyro Gearloose", 2, ZonedDateTime.now())
    val transaction2 = Transaction("Donald duck", "Uncle Scrooge", 5, ZonedDateTime.now())
    val out = storage
      .transfer(transaction1)
      .transfer(transaction2)
    out should equal(
      Storage(Map(), List(
        transaction2,
        transaction1
      )))
  }

  "Storage" should "return not destroy elements from constructor" in {
    val transaction0 = Transaction("Uncle Scrooge", "Huey", 0, ZonedDateTime.now())
    val storage = Storage(Map(), List(transaction0))
    val transaction1 = Transaction("Uncle Scrooge", "Huey", 2, ZonedDateTime.now())
    val transaction2 = Transaction("Uncle Scrooge", "Dewey", 5, ZonedDateTime.now())
    val transaction3 = Transaction("Uncle Scrooge", "Louie", 1, ZonedDateTime.now())
    val out = storage
      .transfer(transaction1)
      .transfer(transaction2)
      .transfer(transaction3)
    out should equal(
      Storage(Map(), List(
        transaction3,
        transaction2,
        transaction1,
        transaction0
      )))
  }

  "Storage.find" should "match to field" in {
    val t1 = Transaction("Donald duck", "Uncle Scrooge", 1, ZonedDateTime.now())
    val t2 = Transaction("Uncle Scrooge", "Goldie O'Gilt", 1, ZonedDateTime.now())
    val storage = Storage(Map(), List(
      t1,
      t2
    ))
    storage.find("Donald duck") should equal(
      List(t1)
    )
  }

  "Storage.find" should "match from field" in {
    val t1 = Transaction("Donald duck", "Uncle Scrooge", 1, ZonedDateTime.now())
    val t2 = Transaction("Uncle Scrooge", "Goldie O'Gilt", 2, ZonedDateTime.now())
    val storage = Storage(Map(), List(
      t1,
      t2
    ))
    storage.find("Goldie O'Gilt") should equal(
      List(t2)
    )
  }

}