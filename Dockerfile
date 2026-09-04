FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -Dmaven.test.skip=true

EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar target/bro-nation-fitness-0.0.1-SNAPSHOT.jar"]
