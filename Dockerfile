


FROM openjdk:11
COPY target/stationSki-1.1.0.jar stationSki-1.1.0.jar
ENTRYPOINT ["java","-jar","/stationSki-1.1.0.jar"]