#!/usr/bin/env bash

BASE_DIR="`dirname \"$0\"`"      
BASE_DIR="`( cd \"$BASE_DIR\" && pwd )`"  
if [ -z "$BASE_DIR" ] ; then
  exit 1  # fail
fi
echo "$BASE_DIR"

applications[0]=supportingServices/discoveryService
applications[1]=supportingServices/mcrs-config
applications[2]=supportingServices/edgeService
applications[3]=appointmentGateway
applications[4]=theProducer
applications[5]=appointmentRequestScheduler
applications[6]=statusService

for i in ${applications[@]}; do
     
	echo "building $i"
	cd $i && ./gradlew $1 $2 $3 $4
	cd $BASE_DIR

done
