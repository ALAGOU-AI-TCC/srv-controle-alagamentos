# alagouai-java/Dockerfile
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# se quiser buildar dentro do container, descomente este bloco:
# COPY . .
# RUN ./mvnw -q -DskipTests package
# COPY target/*.jar /app/app.jar

# se já buildou local (recomendado no começo):
COPY target/*.jar /app/app.jar

ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -Dserver.port=8080"
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
