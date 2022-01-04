FROM openjdk:12-alpine

WORKDIR /

COPY target/user-service-*.jar /user-service.jar

CMD ["java", "-jar", "/user-service.jar"]