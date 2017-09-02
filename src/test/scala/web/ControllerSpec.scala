package web

import java.util.concurrent.{ExecutorService, Executors}

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.Uri
import org.http4s.client.blaze.PooledHttp1Client
import org.http4s.dsl._
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

/**
  * Created by martin on 31/08/17.
  */
class ControllerSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  val serverExecutionPool: ExecutorService = Executors.newSingleThreadExecutor()
  //  implicit val serverExecCtx = ExecutionContext.fromExecutor(serverExecutionPool)

  val random = new java.util.Random()
  //  http://sadhen.com/blog/2016/11/27/http4s-client-intro.html
  val client = PooledHttp1Client()

  override def beforeAll(): Unit = {
    super.beforeAll()
    serverExecutionPool.execute(() => Server.main(Array()))
    // TODO: How can we be sure that server started in the separate thread?
    Thread.sleep(50)
  }

  override def afterAll(): Unit = {
    super.afterAll()
    serverExecutionPool.shutdownNow()
  }

  "Controller" should "read storage" in {
    println("Controller should read storage")
    val sampleEntry = Entry("From_" + random.nextInt(), "To_" + random.nextInt(), random.nextDouble())
    Server.concurrentStorage.reference.reset(Storage(List(sampleEntry)))

    val baseUrl: Uri = Uri.uri("http://127.0.0.1:8080")
    val task = client.expect[String](baseUrl / "transactions")
    val response = task.unsafeAttemptRun()
    response.isRight should equal(true)

    // TODO: minify json?
    response.right.getOrElse(null) should equal("[" + sampleEntry.asJson + "]")
  }

}

