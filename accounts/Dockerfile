#start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#mention who maintains this image
LABEL maintainer="https://github.com/BickeyManandhar"

#copy from where the jar is presented and copy the same jar to the docker image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#this is what we use to start app using the jar from java
#java -jar target/accounts-0.0.1-SNAPSHOT.jar -> here "target/" is not used because of above command,
#we do not have target/ in docker image
#this execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]


#to create image using this file we do : docker build . -t docker_account_username/name_you_want_to_give:version_or_tag_name  from the terminal opened in this same directory
# docker build . -t bickey007/accounts:v1

#to run a container using that image: docker run -d -p 8081:8080 bickey007/accounts:v1
#to run another container of the same image : docker run -d -p 8082:8080 bickey007/accounts:v1
# first port indicates where we want to expose our app to external network
#second port indicated where our container started which cannot be accessed by external network

#some other imp commands
#docker ps
#docker ps -a
#docker start container_id
#docker stop container_id