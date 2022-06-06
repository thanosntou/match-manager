FROM openjdk:17-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:17-alpine
#VOLUME /tmp
#ADD target/*.jar app.jar
#ENV JAVA_OPTS="-Xms64m -Xmx128m --enable-preview"
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]