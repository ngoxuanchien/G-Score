FROM jelastic/maven:3.9.5-openjdk-21 AS maven_tool_chain

WORKDIR /app
COPY . .

RUN mvn compile install -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=maven_tool_chain /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
