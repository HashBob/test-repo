FROM openjdk
LABEL maintainer = "abc@gmail.com"
EXPOSE 9095
WORKDIR /app
COPY target/E_LearningServices.jar /app/E_LearningServices.jar
ENTRYPOINT ["java","-jar","E_LearningServices.jar"]