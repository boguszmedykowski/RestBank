# RestBank
```bash
mvn clean install
mvn spring-boot:run
docker run --name restbank-mysql -e MYSQL_ROOT_PASSWORD=root -v $(pwd)/docker-entrypoint-initdb.d:/docker-entrypoint-initdb.d -d -p 3306:3306 mysql
```

