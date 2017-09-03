package util

import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

import org.http4s.client.blaze.PooledHttp1Client
import org.http4s.{Request, Uri}
import org.scalatest.{BeforeAndAfterAll, Suite}
import web.Server

/**
  * Created by martin on 03/09/17.
  */
trait ServerOps extends BeforeAndAfterAll {
  this: Suite =>

  override def beforeAll(): Unit = {
    super.beforeAll()
    startAndWaitForServe()
  }

  override def afterAll(): Unit = {
    super.afterAll()
    stop()
  }

  val serverExecutionPool: ExecutorService = Executors.newSingleThreadExecutor()
  val client = PooledHttp1Client()

  def start(): Unit = {
    serverExecutionPool.execute(() => {
      try {
        Server.main(Array())
      } catch {
        case _: InterruptedException =>
      }
    })
  }

  def stop(): Unit = {
    serverExecutionPool.shutdownNow()
  }

  def checkServerIsRunning(): Boolean = {
    val baseUrl: Uri = Uri.uri("http://127.0.0.1:8080")
    val task = client.successful(Request(uri = baseUrl / "transactions"))
    task.unsafeAttemptRun().right.getOrElse(false)
  }

  def startAndWaitForServe(): Unit = {
    this.start()
    val timeUnit = TimeUnit.MILLISECONDS
    val waitDuration = 300
    val rounds = 5
    val isRunningAfterWait = Stream.from(0).take(rounds).foldLeft(false)((isStarted: Boolean, _: Int) =>
      if (isStarted) {
        isStarted
      } else {
        Thread.sleep(timeUnit.toMillis(waitDuration))
        checkServerIsRunning()
      })
    if (!isRunningAfterWait) {
      this.stop()
      throw new RuntimeException("Server did not start in " + rounds + " rounds of " + waitDuration + " " + timeUnit.toString.toLowerCase)
    }
  }

}
