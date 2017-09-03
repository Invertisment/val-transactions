package web

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.{Method, Request, Uri}
import org.scalatest.{FlatSpec, Matchers}
import util.{ManualJson, ServerOps, TestEntryFactory}

/**
  * Created by martin on 31/08/17.
  */
class ControllerSpec extends FlatSpec with ServerOps with Matchers with ManualJson with TestEntryFactory {

  //  http://sadhen.com/blog/2016/11/27/http4s-client-intro.html
  val baseUrl: Uri = Uri.uri("http://127.0.0.1:8080")

  "Controller" should "read storage" in {
    val sampleEntry = makeEntry
    Server.concurrentStorage.reference.reset(Storage(List(sampleEntry)))

    val task = client.expect[String](baseUrl / "transactions")
    val response: Either[Throwable, String] = task.unsafeAttemptRun()

    toJson(response) should equal(toJson(List(sampleEntry)))
  }

  "Controller" should "put new items into storage" in {
    val sampleEntry = makeEntry
    val toPut = makeEntry
    Server.concurrentStorage.reference.reset(Storage(List(sampleEntry)))

    val task = client.expect[String](Request(Method.POST, baseUrl / "transactions").withBody(toPut.asJson))
    task.unsafeAttemptRun()

    Server.concurrentStorage.reference.get().entries should equal(List(toPut, sampleEntry))
  }

  "Controller" should "return newly created item from post req" in {
    val sampleEntry = makeEntry
    val toPut = makeEntry
    Server.concurrentStorage.reference.reset(Storage(List(sampleEntry)))

    val task = client.expect[String](Request(Method.POST, baseUrl / "transactions").withBody(toPut.asJson))
    val response: Either[Throwable, String] = task.unsafeAttemptRun()

    toJson(response) should equal(toJson(toPut))
  }

  "Controller" should "search by from" in {
    val entryList: List[Entry] = makeManyEntries()
    Server.concurrentStorage.reference.reset(Storage(entryList))

    val anyEntry: Entry = entryList(random.nextInt(entryList.length))

    val task = client.expect[String](baseUrl / "transactions" / anyEntry.from)
    val response: Either[Throwable, String] = task.unsafeAttemptRun()

    toJson(response) should equal(toJson(List(anyEntry)))
  }

  "Controller" should "search by to" in {
    val entryList: List[Entry] = makeManyEntries()
    Server.concurrentStorage.reference.reset(Storage(entryList))

    val anyEntry: Entry = entryList(random.nextInt(entryList.length))

    val task = client.expect[String](baseUrl / "transactions" / anyEntry.to)
    val response: Either[Throwable, String] = task.unsafeAttemptRun()

    toJson(response) should equal(toJson(List(anyEntry)))
  }

}

