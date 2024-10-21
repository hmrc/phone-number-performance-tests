import sbt._

object Dependencies {

  private val gatlingVersion = "3.6.1"

  val test = Seq(
    "org.slf4j"             % "slf4j-simple"              % "2.0.13"        % Test,
    "com.typesafe"          % "config"                    % "1.3.1"         % Test,
    "uk.gov.hmrc"           %% "performance-test-runner"  % "5.6.0"         % Test,
    "io.gatling"            % "gatling-test-framework"    % gatlingVersion  % Test,
    "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion  % Test,
    "com.typesafe.play"     %% "play-ahc-ws-standalone"   % "2.1.2"         % Test,
    "com.typesafe.play"     %% "play-ws-standalone-json"  % "2.1.2"         % Test,
    "uk.gov.hmrc.mongo"     %% "hmrc-mongo-test-play-30"  % "1.6.0"        % Test,
  )
}

object DependencyOverrides {

  val test = Seq(
    "com.typesafe.akka"          %% "akka-stream"                % "2.6.15"       % Test,
    "com.typesafe.akka"          %% "akka-actor-typed"           % "2.6.15"       % Test,
    "com.typesafe.akka"          %% "akka-protobuf-v3"           % "2.6.15"       % Test,
    "com.typesafe.akka"          %% "akka-serialization-jackson" % "2.6.15"       % Test,

    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8"          % "2.12.3" % Test,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310"        % "2.12.3" % Test,
    "com.fasterxml.jackson.module"   % "jackson-module-parameter-names" % "2.12.3" % Test,
    "com.fasterxml.jackson.module"   % "jackson-module-paranamer"       % "2.12.3" % Test,
    "com.fasterxml.jackson.module"   % "jackson-module-scala_2.13"      % "2.12.3" % Test,
  )

}
