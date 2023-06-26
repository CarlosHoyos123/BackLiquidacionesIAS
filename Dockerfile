FROM openjdk:17.0.1
WORKDIR /BackLiquidacionesIAS
COPY build/libs/liquidacion.jar ./
ENTRYPOINT ["java", "-jar", "app-back-1.0.2.jar"]