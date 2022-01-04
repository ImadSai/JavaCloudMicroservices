FROM openjdk:12-alpine

WORKDIR /

COPY target/gateway-*.jar /gateway.jar

EXPOSE 9090

CMD ["java", "-jar", "/gateway.jar"]