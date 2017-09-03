package web

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.Uri
import org.http4s.dsl._
import org.scalatest.{FlatSpec, Matchers}
import util.ServerOps

import scala.util.parsing.json._

/**
  * Created by martin on 31/08/17.
  */
class ControllerSpec extends FlatSpec with ServerOps with Matchers {

  val random = new java.util.Random()
  //  http://sadhen.com/blog/2016/11/27/http4s-client-intro.html
  val baseUrl: Uri = Uri.uri("http://127.0.0.1:8080")

  def toJson(entries: List[Entry]): Option[JSONType] = JSON.parseRaw(entries.asJson.toString)

  def toJson(response: Either[Throwable, String]): Option[JSONType] = JSON.parseRaw(response.right.getOrElse("{}"))

  "Controller" should "read storage" in {
    val sampleEntry = Entry("From_" + random.nextInt(), "To_" + random.nextInt(), random.nextDouble())
    Server.concurrentStorage.reference.reset(Storage(List(sampleEntry)))

    val task = client.expect[String](baseUrl / "transactions")
    val response: Either[Throwable, String] = task.unsafeAttemptRun()

    toJson(response) should equal(toJson(List(sampleEntry)))
  }

}

