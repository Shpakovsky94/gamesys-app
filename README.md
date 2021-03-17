# gamesys-app

a. Read input data from any external http source (for you to choose, rss feed, twitter feed...)  every x seconds-minutes

b. Process data with any rules (modify input value, add additional values)

c. Put it in any in-memory database (H2 for example)

d. By the request in browser show the last 10 entries (no UI, just json) by reading from the in-memory db

e. Don’t use hibernate (JPA). Use plain SQL.

f. Spring Boot 2, Java

g. No Spring integrations framework

h.       (optional) Create a Docker image for the project, so we can run this as a Docker container

Project structure:

src/

      main/ 

          java/ 

              --here you have classes for process and store data, and defined api to show the data 

      test/ 

          java/ 

              --here you have test classes for reading data, invoke methods from the ‘main’ classes with data -> read from db -> validate results 

Hints:

1) Framework for tests: junit

2) Please, do not spend time on complex processing logic.

3) Run tests

Put it in github. Should be runnable from the IDE. 