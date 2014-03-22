package TagCloud

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.Headers.Names._
import scala.concurrent.duration._
import bootstrap._
import assertions._

class TagCloudExample extends Simulation {

  val httpProtocol = http
    .baseURL("http://tag-cloud.dev")
    .acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.7")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
    .disableFollowRedirect

  val scn = scenario("Test initial Page Load")
            .exec(http("Homepage")
              .get("/"))

  setUp(scn.inject(constantRate(20 usersPerSec) during (30 seconds)))
    .protocols(httpProtocol)
}
