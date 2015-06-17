FROM jeanblanchard/busybox-java:8
COPY  appointmentRequestScheduler-0.1.0.jar .
CMD java -jar  -Dspring.profiles.active=docker appointmentRequestScheduler-0.1.0.jar
EXPOSE 8761