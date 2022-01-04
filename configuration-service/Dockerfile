FROM openjdk:12-alpine

WORKDIR /

COPY target/configuration-service-*.jar /configuration-service.jar

CMD ["java", "-jar", "/configuration-service.jar"]