FROM openjdk:21-jdk
WORKDIR /app
COPY /target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["javar", "jar","app,jar"]