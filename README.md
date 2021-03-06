## Overview

Website: www.catcal.zone
Build: https://travis-ci.org/CatCalZone/mcrs

## Prerequisites

To run the VM you need:
* Virtual Box (https://www.virtualbox.org/)
* Vagrant (https://www.vagrantup.com/)

Windows: The devpack from https://github.com/tknerr/bills-kitchen can be used. 

To run from commandline JDK 1.8 is required and $JAVA_HOME needs to be set to run gradle.

## Running the application

Build all application by running ``buildAll.sh clean build`` in the ``application`` folder.
Go to ``infrastructure/vagrantLocal`` and run ``vagrant up``.

By Commandline: 
Every single application can be started by ``gradlew bootRun`` in the specific folders.

## URLs

* DiscoveryService dashboard: http://localhost:8761/
* RabbitMQ Management: http://localhost:15672/
* testingApp: http://localhost:8765/testingapp/ 

Use the testingApp to send requests to the appointmentGateway and to retrieve the messages from the statusservice.

Insert an appointment request: 
* POST: http://localhost:8765/appointmentRequest

```json
{
    "attendees": [
        "User1", 
        "User2"
    ], 
    "creationDateTimeStamp": 1432110730, 
    "durationInHours": 2,
    "maxEndDateTimeStamp": 1432944000, 
    "minStartDateTimeStamp": 1432944000, 
    "requestUser": "requestUser", 
    "title": "testRequest"
}
```


With 
* GET: http://localhost:8765/appointmentRequest
you can see the created messages.