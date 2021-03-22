# gamesys-app
App will be searching for new Elon Musk Twitter replies every 2 sec and store them to the DB. To get
latest 10 replies call:

http://localhost:8070/get
PS: in case of any problem please restart gamesys-spring-boot container

Stack:
Spring-boot + JDBC + MySQL

MySQL DB user:
root/rootroot

Run these commands from the app folder to start the app:

1) mvnw clean install

2) docker build -t gamesys-spring-boot .

3) docker-compose up

Put it in github. Should be runnable from the IDE. 