# Java Cloud Microservices

### 5 Challenges :

- **_Bounded Context :_** Comment nos services sont découpés.
- **_Configuration Management :_** Comment configurer les instances
- **_Dynamically Scale UP and Scale DOWN and destribute the load_**.
- **_Visibility :_** Une requete peut declancher plusieux MS, on veut savoir exactement lequel a fail et pourquoi, savoir lesquels sont UP et running.
- **_Pack of Cards :_** Eviter que si un service est down que toute notre architecture tombe à l'eau.

### Outils :

#### Spring Cloud Config Server

Nous permet de garder dans un et unique endroit les configurations de nos microservices (environement, variables, ..)

<div style="text-align:center">
    <img src="imgs/springcloudconfigserver.png" alt="SpringCloudConfigServer" width="600"/>
</div>

### Eureka

- Service Registration
- Service Discovery

### Spring Cloud LoadBalancer

- Load Balancing entre les instances d'un service.

## Zipkin Distributed Tracing

- Permet de tracer une requete entre les differentes instances

## Spring Cloud Gateway

-

## Resilience4j

- Fault tolerance, definition de la reponse par defaut si un service est down.

## Ports

| Application                       | Port                  |
| --------------------------------- | --------------------- |
| Limits Service                    | 8080, 8081, ...       |
| Spring Cloud Config Server        | 8888                  |
|                                   |                       |
| Currency Exchange Service         | 8000, 8001, 8002, ..  |
| Currency Conversion Service       | 8100, 8101, 8102, ... |
| Netflix Eureka Naming Server      | 8761                  |
| Netflix Zuul API Gateway Server   | 8765                  |
| Zipkin Distributed Tracing Server | 9411                  |

## URLs

| Application                                  | URL                                                                                                                                                                                      |
| -------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Limits Service                               | http://localhost:8080/limits http://localhost:8080/actuator/refresh (POST)                                                                                                               |
| Spring Cloud Config Server                   | http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev                                                                                                    |
| Currency Converter Service - Direct Call     | http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10                                                                                                                     |
| Currency Converter Service - Feign           | http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000                                                                                                            |
| Currency Exchange Service                    | http://localhost:8000/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR                                                                          |
| Eureka                                       | http://localhost:8761/                                                                                                                                                                   |
| Zuul - Currency Exchange & Exchange Services | http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10 |
| Zipkin                                       | http://localhost:9411/zipkin/                                                                                                                                                            |
| Spring Cloud Bus Refresh                     | http://localhost:8080/actuator/bus-refresh (POST)                                                                                                                                        |
