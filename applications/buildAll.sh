#!/usr/bin/env bash

BASE_DIR="`dirname \"$0\"`"      
BASE_DIR="`( cd \"$BASE_DIR\" && pwd )`"  
if [ -z "$BASE_DIR" ] ; then
  exit 1  # fail
fi
echo "$BASE_DIR"


echo "building mcrs-eureka"
cd supportingServices/mcrs-eureka && ./gradlew clean build 
cd $BASE_DIR

echo "building mcrs-config"
cd supportingServices/mcrs-config && ./gradlew clean build 
cd $BASE_DIR

echo "building mcrs-zuul"
cd supportingServices/mcrs-config && ./gradlew clean build 
cd $BASE_DIR

echo "building appointmentGateway"
cd supportingServices/appointmentGateway && ./gradlew clean build 
cd $BASE_DIR

echo "building theProducer"
cd theProducer && ./gradlew clean build 
cd $BASE_DIR