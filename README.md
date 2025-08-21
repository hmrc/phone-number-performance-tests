# phone-number-performance-tests

Performance test suite for the `phone-number`, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.

## Running the tests

### Prerequisites

Prior to executing the tests ensure you have:

* Docker - to start mongo container
* Installed/configured service manager 2 [sm2](https://github.com/hmrc/sm2)

### Starting services locally

If Mongo is not already running, then start via docker:

```shell
  docker run --restart unless-stopped --name mongodb -p 27017:27017 -d percona/percona-server-mongodb:7.0 --replSet rs0
  docker exec -it mongodb mongosh --eval "rs.initiate();"
```

Start the dependent microservices using the following shell script:

```shell
  ./start_services.sh
```

## Tests execution

### Local Testing

To run a local smoke test with one user run the following script:

```shell
  ./run_tests.sh
```

To run a local run with all users, you can add `false` as an argument to the script which sets `smoke` to `false`:

```shell
  ./run_tests.sh false
```

### Running against staging environment

To run a full performance test against staging environment there is a Jenkins job on performance Jenkins. You should **only** run tests against Staging from within Jenkins. 

## Logging

The template uses [logback.xml](src/test/resources) to configure log levels. The default log level is *WARN*. This can be updated to use a lower level for example *TRACE* to view the requests sent and responses received during the test.

## Scalafmt
 
This repository uses [Scalafmt](https://scalameta.org/scalafmt/), a code formatter for Scala. The formatting rules configured for this repository are defined within [.scalafmt.conf](.scalafmt.conf).

To apply formatting to this repository using the configured rules in [.scalafmt.conf](.scalafmt.conf) execute:

```shell
  sbt scalafmtAll
```

 To check files have been formatted as expected execute:

```shell
 sbt scalafmtCheckAll scalafmtSbtCheck
```

[Visit the official Scalafmt documentation to view a complete list of tasks which can be run.](https://scalameta.org/scalafmt/docs/installation.html#task-keys)
