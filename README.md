
# Value transactions

API tests are run with unit tests

To run the server: `sbt run`

To run the tests: `sbt test`

To produce binary package: `sbt assembly`

## Requests

### All transactions

`curl -X GET "127.0.0.1:8080/transactions"`

`[{"from":"Uncle Scrooge","to":"Princess Oona","amount":40}]`

### Transactions that include a person

`curl -X GET "127.0.0.1:8080/transactions/Donald%20duck"`

`[{"from":"Uncle Scrooge","to":"Princess Oona","amount":40}]`

### New transaction

`curl -v -X POST "127.0.0.1:8080/transactions" --data '{"from":"Donald duck", "to":"Gyro Gearloose", "amount":1337}'`

`{"from":"Donald duck","to":"Gyro Gearloose","amount":1337}`

