package TestApi

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.Headers.Names._
import scala.concurrent.duration._
import bootstrap._
import assertions._

class LoadTimes extends Simulation {

  val httpProtocol = http
    .baseURL("http://test-api.dev/")
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .disableFollowRedirect

  val scn = scenario("Test Sample APi Times")
              .exec(http("Get Hello World")
                .get("/api/hello")
                .check(status.is(200)))
              .exec(http("Longer waits")
                .get("/api/error")
                .check(status.is(500)))

  setUp(scn.inject(constantRate(15 usersPerSec) during (15 seconds)))
    .protocols(httpProtocol)
}
