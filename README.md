# bet-challenge
BetVictor
Interview Task – Software Engineer-Java

How to run it?
Open 3 terminals

Terminal 1.
Go to bet-challenge directory and run: 
docker-compose up

Terminal 2. Generate random text, process text and publish messages.
Go to /bet-challenge/processing-service directory and run:

./mvnw package

java -jar target/processing-service-0.0.1.jar

Terminal 3. Consume messages, store them in H2 DB
Go to /bet-challenge/repository-service and run:

./mvnw package

java -jar target/repository-service-0.0.1.jar


1. Processing application. Hit the API many times
swagger: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/processing-controller/getText
endpoint: http://localhost:8080/betvictor/text?p_start=1&p_end=10&w_count_min=1&w_count_max=25

2. Repository application. See history
swagger: http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/repository-controller/getHistory
endpoint: http://localhost:8081/betvictor/history
