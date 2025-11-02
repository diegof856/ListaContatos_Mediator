#build
FROM maven:4.0.0-rc-4-amazoncorretto-21-al2023 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests
# run
FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build ./build/target/*.jar ./app.jar

EXPOSE 8080
ENV DATASOURCE_URL=""
ENV PASS=""
ENV TZ="America/Sao_Paulo"

ENTRYPOINT ["java", "-jar", "app.jar"]
