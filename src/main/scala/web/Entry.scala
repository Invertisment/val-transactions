package web

/**
  * Created by martin on 21/08/17.
  */
object Entry {

  val infiniteMoney = "Uncle Scrooge"

  def apply(egoist: String, supply: BigDecimal): Entry = Entry(Entry.infiniteMoney, egoist, supply)
}

case class Entry(altruist: String, egoist: String, supply: BigDecimal)