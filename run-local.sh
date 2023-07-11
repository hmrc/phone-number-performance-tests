#!/usr/bin/env bash

SMOKE_TEST=true
FULL=$1

if [ -z "$FULL" ]; then
  echo "boolean value not set, defaulting to Smoke Test: $SMOKE_TEST"
  echo ""
fi

if [ $(docker container inspect -f '{{.State.Status}}' mongo ) == "running" ];
then
    echo "Mongo is already running!"
    echo ""
else
    echo "Mongo is not running and starting it now!"
    echo ""
    docker run --rm -d --name mongo -d -p 27017:27017 mongo:4.0
    echo "Mongo is started now!"
    echo ""
fi

sm --start PHONE_NUMBER_ALL -r
sbt -Dperftest.runSmokeTest=${FULL:=$SMOKE_TEST} -DrunLocal=true gatling:test
sm --stop PHONE_NUMBER_ALL -r
