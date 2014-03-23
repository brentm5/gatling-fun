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

  val scn = scenario("Test Page Load Times")
            .exec(http("Get API Hello World")
              .get("/api/hello"))

  setUp(scn.inject(constantRate(13 usersPerSec) during (30 seconds)))
    .protocols(httpProtocol)
}
