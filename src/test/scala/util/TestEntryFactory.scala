package util

import java.util.Random

import web.Entry

/**
  * Created by martin on 03/09/17.
  */
trait TestEntryFactory {

  val random = new Random()

  def makeEntry = Entry("From_" + random.nextInt(), "To_" + random.nextInt(), random.nextDouble())

  def makeManyEntries(): List[Entry] = Stream.from(0).take(5).map(_ => makeEntry).toList

}
