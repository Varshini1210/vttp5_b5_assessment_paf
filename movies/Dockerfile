FROM eclipse-temurin:21-jdk AS compiler

ARG COMPILE_DIR=/code_folder

WORKDIR ${COMPILE_DIR}


COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn
COPY ../data/movies_post_2010.zip ../data/movies_post_2010.zip


RUN chmod a+x ./mvnw && ./mvnw clean package -Dmaven.test.skip=true


ENTRYPOINT java -jar target/movies-0.0.1-SNAPSHOT.jar

#stage 2

FROM eclipse-temurin:21-jdk

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}

COPY --from=compiler /code_folder/target/movies-0.0.1-SNAPSHOT.jar assessment.jar
COPY --from=compiler ../data/movies_post_2010.zip ../data/movies_post_2010.zip

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "assessment.jar"]