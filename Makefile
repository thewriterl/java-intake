down:
	docker-compose down

build:
	mvn clean package -DskipTests

database:
	docker-compose up

install:
	mvn install -DskipTests

application:
	java -jar target/java-intake-0.0.1-SNAPSHOT.jar

up: down build database

test:
	mvn verify
