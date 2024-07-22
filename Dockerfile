# Usa una imagen base de Java
FROM openjdk:11-jdk-slim

# Configura el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación en el contenedor
COPY target/microservicios-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
