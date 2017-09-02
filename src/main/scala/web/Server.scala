package web

import java.util.concurrent.Executors

import fs2.{Stream, Task}
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.StreamApp

import scala.concurrent.ExecutionContext

/**
  * Created by martin on 21/08/17.
  */
object Server extends StreamApp {

  val port: Int = 8080
  val ip: String = "0.0.0.0"
  val executorService: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())
  val concurrentStorage = ConcurrentStorage(Storage(List()))

  override def stream(args: List[String]): Stream[Task, Nothing] =
    BlazeBuilder
      .bindHttp(port, ip)
      .mountService(ServiceFactory.createService(concurrentStorage))
      .withExecutionContext(executorService)
      .serve

}
