FROM maven:3.8-jdk-17-slim AS builder
WORKDIR /app
COPY . .
ENV JAVA_HOME=/usr/lib/jvm/java-17-oracle
RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk17:jre-17.0.1_12-alpine
WORKDIR /app
COPY --from=builder /target/GateWay-0.0.1-SNAPSHOT.jar /app/GateWay-0.0.1-SNAPSHOT.jar
EXPOSE 8800
ENTRYPOINT ["java", "-jar", "GateWay-0.0.1-SNAPSHOT.jar"]
