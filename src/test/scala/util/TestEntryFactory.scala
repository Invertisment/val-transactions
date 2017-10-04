package util

import java.time.{ZoneId, ZonedDateTime}
import java.util.Random

import web.Transaction

/**
  * Created by martin on 03/09/17.
  */
trait TestEntryFactory {

  val random = new Random()

  def makeTransaction = Transaction("From_" + random.nextInt(),
    "To_" + random.nextInt(),
    random.nextDouble(),
    ZonedDateTime.now(ZoneId.systemDefault()))

  def makeManyTransactions(): List[Transaction] = Stream.from(0).take(5).map(_ => makeTransaction).toList

}
