# Coffee Shop API

This api is used to manage a coffee shop chain. Through the api, customers can order coffee and operators can process those orders.


## Technologies used
* [Spring Boot](https://spring.io/projects/spring-boot) -> 2.7.0
* [Liquibase](https://www.liquibase.org) -> 4.9.1
* [postgres](https://www.sqlite.org/index.html)
* [docker](https://www.docker.com)
* [git](https://git-scm.com)


## SetUp
First setup a postgres DB and change the application.yaml files in every module with the postgres DB configurations.

Then build the docker images using build.sh(Linux based Env) or build.bat(windows based Env).
To build projects successfully, maven should be installed in the Env. If not, build the maven projects manually in following order. 

* auth-service         
* shop-service         
* reservation-service   
* queue-service       

and comment out the following section.
```
mvn -f auth-service/pom.xml clean install -Dmaven.test.skip
mvn -f shop-service/pom.xml clean install -Dmaven.test.skip
mvn -f reservation-service/pom.xml clean install -Dmaven.test.skip
mvn -f queue-service/pom.xml clean install -Dmaven.test.skip
```
After successfully building the images, deploy docker images using run.sh(Linux based Env) or Run.bat(Windows based Env).

## Notes
Port numbers for different services are as follows.
* auth-service          : 8081
* shop-service          : 8082
* reservation-service   : 8083
* queue-service         : 8084