@REM run docker images
start docker run -p 8081:8081 --name auth-service lakshitha/coffee_shop:auth-service
start docker run -p 8082:8082 --name shop-service lakshitha/coffee_shop:shop-service
start docker run -p 8083:8083 --name reservation-service lakshitha/coffee_shop:reservation-service
start docker run -p 8084:8084 --name queue-service lakshitha/coffee_shop:queue-service