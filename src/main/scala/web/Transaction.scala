package web

import java.time.ZonedDateTime

import io.circe.{Decoder, Encoder}

/**
  * Created by martin on 21/08/17.
  */

trait BaseTO {
  //  https://www.scala-exercises.org/circe/Encoding%20and%20decoding
  import java.time.ZonedDateTime

  import cats.syntax.either._

  implicit val encodeZonedDateTime: Encoder[ZonedDateTime] = Encoder.encodeString.contramap[ZonedDateTime](_.toString)
  implicit val decodeZonedDateTime: Decoder[ZonedDateTime] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(ZonedDateTime.parse(str)).leftMap(_ => "ZonedDateTime")
  }
}

object Transaction extends BaseTO {}

case class Transaction(from: String, to: String, amount: BigDecimal, time: ZonedDateTime)

