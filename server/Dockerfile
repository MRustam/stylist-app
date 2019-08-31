FROM hirokimatsumoto/alpine-openjdk-11
VOLUME /tmp
ADD build/libs/server.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]