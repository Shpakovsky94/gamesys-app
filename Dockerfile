FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/gamesys-spring-boot.jar gamesys-spring-boot.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "gamesys-spring-boot.jar"]
