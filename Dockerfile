# 1) Base image: Java 17 runtime
FROM eclipse-temurin:17-jdk-alpine

# 2) Jar dosyasını kopyala
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# 4) (Opsiyonel) JVM parametreleri için hook
ENV JAVA_OPTS=""

EXPOSE 8086

# 5) Uygulamayı başlat
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]