# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en el contenedor
COPY target/transactionservice-0.0.1-SNAPSHOT.jar /app/transactionservice-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que correrá la aplicación
EXPOSE 8080

# LEVANTAR NUESTRA APLICACION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java","-jar","/app/transactionservice-0.0.1-SNAPSHOT.jar"]