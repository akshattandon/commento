# codetestbackend

This is a "microservice" application intended to be part of a microservice architecture


## PreRequisites
To Run this microservice you need to have java installed in your system and JAVA_HOME envoirnment variable

 
## Development
To start your application in the dev profile, run:
### In Linux
```
./gradlew
```
### In Windows
```
gradlew.bat

```
### Packaging as jar

To build the final jar and optimize the codetestbackend application for production, run:

```
./gradlew -Pprod clean bootJar
or 
gradlew.bat -Pprod clean bootJar
```

To ensure everything worked, run:

```
java -jar build/libs/*.jar
```


### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./gradlew -Pprod -Pwar clean bootWar

```

## Testing ( In Progress )

To launch your application's tests, run:

```
./gradlew test integrationTest jacocoTestReport
```

Report will be placed at commentobackend\build\reports\jacoco\test\html\index.html 

### Code quality ( In Progress )
Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the gradle plugin.

Then, run a Sonar analysis:

```
./gradlew -Pprod clean check jacocoTestReport sonarqube
```

For more information, refer to the [Code quality page][].

## Docker ( In Progress )

### PreRequisites
Docker installed locally 

### Development 
For example, to start a mysql database in a docker container, run:

```
docker-compose -f src/main/docker/mysql.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/mysql.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
./gradlew bootJar -Pprod 
```
Then copy codetestbackend*.jar from build/libs folder to src/main/docker/ folder
Then run from src/main/docker/
```
docker build -t codetestbackend . 
```
Then run from codetestbackend folder
```
docker-compose -f src/main/docker/app.yml up -d
```
## Continuous Integration (In Progress)
