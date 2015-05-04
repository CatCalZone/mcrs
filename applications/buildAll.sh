#!/usr/bin/env bash

echo "building mcrs-eureka"
cd mcrs-eureka && ./gradlew clean build && cd ..
echo "building mcrs-config"
cd mcrs-config && ./gradlew clean build && cd ..
echo "building appointmentGateway"
cd appointmentGateway && ./gradlew clean build && cd ..
echo "building theProducer"
cd theProducer && ./gradlew clean build && cd ..