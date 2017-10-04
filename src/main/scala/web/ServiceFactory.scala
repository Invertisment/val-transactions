package web

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl._
import Transaction._

/**
  * Created by martin on 21/08/17.
  */
object ServiceFactory {

  def createService(storage: ConcurrentStorage) = HttpService {

    /**
      * All transactions
      * curl -X GET "127.0.0.1:8080/transactions"
      * [{"from":"Uncle Scrooge","to":"Princess Oona","amount":40}]
      */
    case GET -> Root / "transactions" => Ok(storage.reference.get().entries.asJson)

    /**
      * Transactions that include a person
      * curl -X GET "127.0.0.1:8080/transactions/Donald%20duck"
      * [{"from":"Uncle Scrooge","to":"Princess Oona","amount":40}]
      */
    case GET -> Root / "transactions" / name => Ok(storage.reference.get().find(name).asJson)

    /**
      * New transaction
      * curl -v -X POST "127.0.0.1:8080/transactions" --data '{"from":"Donald duck", "to":"Gyro Gearloose", "amount":1337}'
      * {"from":"Donald duck","to":"Gyro Gearloose","amount":1337}
      */
    case req@POST -> Root / "transactions" =>
      for {
        entry <- req.as(jsonOf[Transaction])
        response <- Created(storage.transfer(entry).asJson)
      } yield response

  }
}
