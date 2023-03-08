import sbt._

object Dependencies {

  private val gatlingVersion = "3.4.2"

  val test = Seq(
    "com.typesafe"          % "config"                    % "1.3.1"         % Test,
    "uk.gov.hmrc"           %% "performance-test-runner"  % "5.3.0"         % Test,
    "io.gatling"            % "gatling-test-framework"    % gatlingVersion  % Test,
    "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion  % Test,
    "com.typesafe.play"     %% "play-ahc-ws-standalone"   % "2.1.2"         % Test,
    "com.typesafe.play"     %% "play-ws-standalone-json"  % "2.1.2"         % Test,
    "uk.gov.hmrc.mongo"     %% "hmrc-mongo-test-play-28"  % "0.71.0"        % Test
  )
}
