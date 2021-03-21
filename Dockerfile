FROM openjdk:8
ADD ./target/gamesys-spring-boot.jar gamesys-spring-boot.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","gamesys-spring-boot.jar"]