@REM build project using maven (Maven should be installed. Or else you can manually build the project in this order and comment this section)
call mvn -f auth-service\pom.xml clean install -Dmaven.test.skip
call mvn -f shop-service\pom.xml clean install -Dmaven.test.skip
call mvn -f reservation-service\pom.xml clean install -Dmaven.test.skip
call mvn -f queue-service\pom.xml clean install -Dmaven.test.skip

@REM build docker images 
call docker build -t auth-service auth-service/
call docker build -t shop-service shop-service/
call docker build -t reservation-service reservation-service/
call docker build -t queue-service queue-service/
 
@REM tag images 
call docker tag auth-service lakshitha/coffee_shop:auth-service
call docker tag shop-service lakshitha/coffee_shop:shop-service
call docker tag reservation-service lakshitha/coffee_shop:reservation-service
call docker tag queue-service lakshitha/coffee_shop:queue-service
