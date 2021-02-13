FROM adoptopenjdk/openjdk11:alpine-slim

#ENV DATA_DIR=/inventory-api
ENV DB_HOST=172.17.0.1

RUN addgroup -S spring && adduser -S spring -G spring
#RUN mkdir -p $DATA_DIR
#RUN chown spring $DATA_DIR

USER spring:spring
ARG JAR_FILE=./target/inventory-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]