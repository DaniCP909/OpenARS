FROM maven:3.9.0-eclipse-temurin-17 as builder
COPY . /code/
WORKDIR /code
RUN mvn package -DskipTests



FROM eclipse-temurin:17.0.6_10-jre
COPY --from=builder /code/target/CommentService-0.0.1-SNAPSHOT.jar /
CMD ["java", "-jar", "CommentService-0.0.1-SNAPSHOT.jar"]
