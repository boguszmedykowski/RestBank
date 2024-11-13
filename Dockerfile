# Wybór obrazu bazowego z JDK 21 na Alpine Linux
FROM eclipse-temurin:21-jdk-alpine

# Instalacja basha i mavena
RUN apk add --no-cache bash maven

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie pliku pom.xml, aby Docker mógł zbuforować zależności Maven
COPY pom.xml .

# Pobranie zależności Maven offline
RUN mvn dependency:go-offline

# Kopiowanie całego kodu źródłowego do kontenera
COPY . .

# Budowa aplikacji przy pominięciu testów
RUN mvn clean package -DskipTests

# Kopiowanie skryptu wait-for-it.sh do głównego katalogu
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Komenda ENTRYPOINT - oczekiwanie na bazę danych i uruchomienie aplikacji
ENTRYPOINT ["/bin/bash", "/wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app/target/RestBank-0.0.1-SNAPSHOT.jar"]