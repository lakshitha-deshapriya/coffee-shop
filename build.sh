#!/bin/bash

# build project using maven (Maven should be installed. Or else you can manually build the project in this order and comment this section)
mvn -f auth-service/pom.xml clean install -Dmaven.test.skip
mvn -f shop-service/pom.xml clean install -Dmaven.test.skip
mvn -f reservation-service/pom.xml clean install -Dmaven.test.skip
mvn -f queue-service/pom.xml clean install -Dmaven.test.skip

# build docker images
docker build -t auth-service auth-service/
docker build -t shop-service shop-service/
docker build -t reservation-service reservation-service/
docker build -t queue-service queue-service/

# tag docker images
docker tag auth-service lakshitha/coffee_shop:auth-service
docker tag shop-service lakshitha/coffee_shop:shop-service
docker tag reservation-service lakshitha/coffee_shop:reservation-service
docker tag queue-service lakshitha/coffee_shop:queue-service