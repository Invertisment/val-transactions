package web

import io.circe._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._

/**
  * Created by martin on 21/08/17.
  */
object Controller {
  val service = HttpService {
    case GET -> Root / "info" / name =>
      Ok(Json.obj("message" -> Json.fromString(s"""Hello, $name""")))
    case POST -> Root / "hello" / name =>
      Ok(Json.obj("message" -> Json.fromString(s"""Hello, $name""")))
  }
}
