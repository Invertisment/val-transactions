package util

import io.circe.generic.auto._
import io.circe.syntax._
import web.Transaction
import web.Transaction._

import scala.util.parsing.json._

/**
  * Created by martin on 03/09/17.
  */
trait ManualJson {

  def toJson(entries: List[Transaction]): Option[JSONType] = JSON.parseRaw(entries.asJson.toString)

  def toJson(entry: Transaction): Option[JSONType] = JSON.parseRaw(entry.asJson.toString)

  def toJson(response: Either[Throwable, String]): Option[JSONType] = JSON.parseRaw(response.right.getOrElse("{}"))

}
