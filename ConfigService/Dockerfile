FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine3.13

WORKDIR /app

COPY --from=builder /app/target/ConfigService-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "ConfigService-0.0.1-SNAPSHOT.jar"]
