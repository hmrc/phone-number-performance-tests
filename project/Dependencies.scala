import sbt._

object Dependencies {

  private val gatlingVersion = "3.6.1"

  val test = Seq(
    "com.typesafe"          % "config"                    % "1.3.1"         % Test,
    "uk.gov.hmrc"           %% "performance-test-runner"  % "5.6.0"         % Test,
    "io.gatling"            % "gatling-test-framework"    % gatlingVersion  % Test,
    "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion  % Test,
    "com.typesafe.play"     %% "play-ahc-ws-standalone"   % "2.1.2"         % Test,
    "com.typesafe.play"     %% "play-ws-standalone-json"  % "2.1.2"         % Test,
    "uk.gov.hmrc.mongo"     %% "hmrc-mongo-test-play-28"  % "0.71.0"        % Test,

    "com.typesafe.akka"     % "akka-stream"                % "2.6.15"       % Test,
    "com.typesafe.akka"     % "akka-actor-typed"           % "2.6.15"       % Test,
    "com.typesafe.akka"     % "akka-protobuf-v3"           % "2.6.15"       % Test,
    "com.typesafe.akka"     % "akka-serialization-jackson" % "2.6.15"       % Test
  )
}

// object DependencyOverrides {

//   val test = Seq(
//     "com.typesafe.akka"     % "akka-stream"                % "2.6.15"       % Test,
//     "com.typesafe.akka"     % "akka-actor-typed"           % "2.6.15"       % Test,
//     "com.typesafe.akka"     % "akka-protobuf-v3"           % "2.6.15"       % Test,
//     "com.typesafe.akka"     % "akka-serialization-jackson" % "2.6.15"       % Test
//   )

// }
