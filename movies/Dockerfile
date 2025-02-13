FROM eclipse-temurin:23-jdk AS compiler

ARG COMPILE_DIR=/code_folder

WORKDIR ${COMPILE_DIR}

#COPY movies/. .
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn
# COPY movies_post_2010.zip data/movies_post_2010.zip
COPY movies_post_2010.zip movies_post_2010.zip


RUN chmod a+x ./mvnw && ./mvnw clean package -Dmaven.test.skip=true
# RUN chmod a+x movies/mvnw && movies/mvnw clean package -Dmaven.test.skip=true


ENTRYPOINT java -jar target/movies-0.0.1-SNAPSHOT.jar

#stage 2

FROM eclipse-temurin:23-jdk

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}

COPY --from=compiler /code_folder/target/movies-0.0.1-SNAPSHOT.jar assessment.jar
COPY --from=compiler /code_folder/movies_post_2010.zip movies_post_2010.zip


ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "assessment.jar"]