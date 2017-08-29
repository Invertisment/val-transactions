package web

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl._

/**
  * Created by martin on 21/08/17.
  */
object Controller {

  val service = HttpService {

    case GET -> Root / "transactions" => Ok(Storage.reference.get().asJson)

    case GET -> Root / "transactions" / name => Ok()

    //    case POST -> Root / "transactions" / owner / beneficiary => Ok()
  }
}
