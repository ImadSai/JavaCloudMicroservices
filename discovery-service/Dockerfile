FROM openjdk:12-alpine

WORKDIR /

COPY target/discovery-service-*.jar /discovery-service.jar

EXPOSE 8761

CMD ["java", "-jar", "/discovery-service.jar"]