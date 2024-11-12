# RestBank

Change name of the file `.example.application.properties` to `application.properties` and fill it with your own data.

```bash

docker run --name restbank-mysql -e MYSQL_ROOT_PASSWORD=root -v $(pwd)/docker-entrypoint-initdb.d:/docker-entrypoint-initdb.d -d -p 3306:3306 mysql
mvn clean install
mvn spring-boot:run
or
docker-compose up --build
```
Dokumentacja Swagger dostępna pod adresem:
http://207.154.192.20:8080/swagger-ui/index.html


