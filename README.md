# phone-number-performance-tests
Performance test suite for the `phone-number`, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.

## Running the tests

Prior to executing the tests ensure you have:

* Docker - to start mongo container
* Installed/configured service manager

Run the following command to start the mongo db and the services locally:
```
docker run --rm -d --name mongo -d -p 27017:27017 mongo:4.0
sm2 --start PHONE_NUMBER_ALL
```

Using the `--wait 100` argument ensures a health check is run on all the services started as part of the profile. `100` refers to the given number of seconds to wait for services to pass health checks.

## Logging

The template uses [logback.xml](src/test/resources) to configure log levels. The default log level is *WARN*. This can be updated to use a lower level for example *TRACE* to view the requests sent and responses received during the test.

## Tests execution
### Smoke test

It might be useful to try the journey with one user to check that everything works fine before running the full performance test

```
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

or use the script `run_tests.sh` to execute the smoke tests locally:

`./run_tests.sh <environment>`

The tests default to the `local` environment.  For a complete list of supported param values, see:
`src/test/resources/application.conf` for **environment**

### Running the full performance test (It is not advised to run the full suite of tests locally)
```
sbt -DrunLocal=true gatling:test
```
### Run the example test against staging environment

To run a full performance test against staging environment, implement a job builder and run the test **only** from Jenkins.

### Scalafmt
 This repository uses [Scalafmt](https://scalameta.org/scalafmt/), a code formatter for Scala. The formatting rules configured for this repository are defined within [.scalafmt.conf](.scalafmt.conf).

 To apply formatting to this repository using the configured rules in [.scalafmt.conf](.scalafmt.conf) execute:

 ```
 sbt scalafmtAll
 ```

 To check files have been formatted as expected execute:

 ```
 sbt scalafmtCheckAll scalafmtSbtCheck
 ```

[Visit the official Scalafmt documentation to view a complete list of tasks which can be run.](https://scalameta.org/scalafmt/docs/installation.html#task-keys)
