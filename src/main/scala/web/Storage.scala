package web

import Atom.Atom

/**
  * Created by martin on 21/08/17.
  */
object ConcurrentStorage {
  def apply(storage: Storage): ConcurrentStorage = ConcurrentStorage(Atom[Storage](storage))

  def apply(): ConcurrentStorage = ConcurrentStorage(Storage(Map(), List()))
}

case class ConcurrentStorage(reference: Atom[Storage]) {

  def transfer(entry: Transaction): Transaction = {
    reference swap {
      _.transfer(entry)
    }
    entry
  }
}

case class Storage(deposits: Map[String, Deposit], entries: List[Transaction]) {

  def transfer(entry: Transaction): Storage =
    Storage(deposits, entries.+:(entry))

  def find(accountName: String): List[Transaction] =
    entries.filter(e => e.from == accountName || e.to == accountName)

}

