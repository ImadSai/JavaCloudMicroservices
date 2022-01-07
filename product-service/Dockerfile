FROM openjdk:12-alpine

WORKDIR /

COPY target/product-service-*.jar /product-service.jar

CMD ["java", "-jar", "/product-service.jar"]