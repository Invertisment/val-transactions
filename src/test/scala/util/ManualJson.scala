package util

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.{Method, Request, Uri}
import org.scalatest.{FlatSpec, Matchers}
import util.ServerOps
import web.Entry

import scala.util.parsing.json._

/**
  * Created by martin on 03/09/17.
  */
trait ManualJson {

  def toJson(entries: List[Entry]): Option[JSONType] = JSON.parseRaw(entries.asJson.toString)

  def toJson(entry: Entry): Option[JSONType] = JSON.parseRaw(entry.asJson.toString)

  def toJson(response: Either[Throwable, String]): Option[JSONType] = JSON.parseRaw(response.right.getOrElse("{}"))

}
