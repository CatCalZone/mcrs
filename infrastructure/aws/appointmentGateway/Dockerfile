FROM jeanblanchard/busybox-java:8
COPY  appointmentGateway-0.1.0.jar .
CMD java -jar  -Dspring.profiles.active=docker appointmentGateway-0.1.0.jar
EXPOSE 8761