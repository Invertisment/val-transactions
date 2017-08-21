package web

import scala.collection.mutable
import scala.concurrent.Future

/**
  * Created by martin on 21/08/17.
  */
object Storage {
  private val entries: mutable.MutableList[Entry] = mutable.MutableList[Entry]()

  def add(entry: Entry): Future[Entry] = entries.synchronized {
    //    entries += entry
    println(entry)
    Future(entry)
  }
}

case class Entry(key: String, value: Double)
