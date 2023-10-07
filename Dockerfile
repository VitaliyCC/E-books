FROM openjdk:17
ADD /target/E-books-0.0.1-SNAPSHOT.jar e-books.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "e-books.jar"]
