FROM openjdk:latest
COPY target/startWiremock-0.1-jar-with-dependencies.jar startWiremock-0.1-jar-with-dependencies.jar
RUN mkdir -p /src/test/resources/__files/
COPY target/src/test/resources/__files/*.json /src/test/resources/__files/
ENTRYPOINT ["java", "-jar", "startWiremock-0.1-jar-with-dependencies.jar"]