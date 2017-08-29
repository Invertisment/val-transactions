package web

import Atom.Atom

/**
  * Created by martin on 21/08/17.
  */
object Storage {
  val reference: Atom[Storage] = Atom[Storage](Storage(List()))

  def transfer(entry: Entry): Storage =
    reference swap {
      _.transfer(entry)
    }
}

case class Storage(entries: List[Entry]) {

  def transfer(entry: Entry): Storage =
    Storage(entries.+:(entry))

  def sum(): Map[String, BigDecimal] =
    Map[String, BigDecimal](
      "Uncle Scrooge" -> -2,
      "Donald duck" -> 2
    )

  //  def sum(): Map[String, BigDecimal] =
  //    entries.get().foldLeft(Map[String, BigDecimal]())((balances: Map[String, BigDecimal], current: Entry) => {
  //      val beneficiaryBalance: BigDecimal = balances.getOrElse(current.beneficiary, 0)
  //      val recipientBalance: BigDecimal = balances.getOrElse(current.recipient, 0)
  //      val removed = balances + (current.recipient -> (recipientBalance - current.amount))
  //      val added = removed + (current.beneficiary -> (current.amount + beneficiaryBalance))
  //      added
  //    })
}

