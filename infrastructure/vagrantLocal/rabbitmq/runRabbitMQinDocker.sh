#!/usr/bin/env bash
docker run -d -e RABBITMQ_NODENAME=my-rabbit -p 5672:5672  --name theRabbit rabbitmq:3
