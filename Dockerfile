# Dockerfile simplifié : utilise le JAR pré-compilé
# Cas d'usage: docker build -t stringvalidator:1.0 .
# (suppose que mvn clean package a déjà été exécuté localement)

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copie le JAR pré-construit localement
COPY target/stringvalidator-1.0.0.jar /app/stringvalidator.jar

# Expose port (optionnel)
EXPOSE 8080

# Point d'entrée : exécute l'application
ENTRYPOINT ["java", "-jar", "/app/stringvalidator.jar"]
