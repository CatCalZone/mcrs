FROM jeanblanchard/busybox-java:8
COPY edgeService-0.1.0.jar .
CMD java -jar -Dspring.profiles.active=docker  edgeService-0.1.0.jar
EXPOSE 8761