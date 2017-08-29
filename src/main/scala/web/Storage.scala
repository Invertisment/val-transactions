package web

import Atom.Atom

/**
  * Created by martin on 21/08/17.
  */
object Storage {
  val reference: Atom[Storage] = Atom[Storage](Storage(List(

    Entry("Donald duck", "Uncle Scrooge", 10),
    Entry("Donald duck", "Uncle Scrooge", 50),
    Entry("Uncle Scrooge", "Goldie O'Gilt", 20),
    Entry("Uncle Scrooge", "Princess Oona", 40)

  )))

  def transfer(entry: Entry): Entry = {
    reference swap {
      _.transfer(entry)
    }
    entry
  }
}

case class Storage(entries: List[Entry]) {

  def transfer(entry: Entry): Storage =
    Storage(entries.+:(entry))

  def find(accountName: String): List[Entry] =
    entries.filter(e => e.from == accountName || e.to == accountName)

}

