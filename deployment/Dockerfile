FROM amazoncorretto:17-alpine
#VOLUME /tmp
#WORKDIR app
COPY *.jar liquidacion.jar
ENV JAVA_OPTS=" -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
# Replace with a non-root user to avoid running the container with excessive privileges
#USER CarlosHoyos
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar liquidacion.jar" ]
