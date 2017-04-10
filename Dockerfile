###FROM openjdk:8-jdk-alpine
###ENV LANG en_SG.UTF-8
###ENV LANGUAGE en_SG:en
###ENV LC_ALL en_SG.UTF-8
###
###RUN apk add --no-cache --update curl
###ADD friendmanagementcli.jar app.jar
###
###RUN sh -c 'touch /app.jar'
###ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

FROM maven:3-jdk-8-alpine

ADD ./ /friendmanagement
RUN cd /friendmanagement && mvn clean package
RUN cp /friendmanagement/target/friendmanagementcli.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xmx1024m"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
