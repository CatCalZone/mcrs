FROM jeanblanchard/busybox-java:8
COPY  statusService-0.1.0.jar .
CMD java -jar  -Dspring.profiles.active=docker statusService-0.1.0.jar
EXPOSE 8761