# StringValidator - Application Java 21

Application de validation de chaînes de caractères avec sûreté, robustesse et fiabilité.

## 🎯 Objectifs

- **Sûreté** : Validation de noms avec lettres accentuées
- **Robustesse** : Gestion des valeurs null
- **Fiabilité** : Calcul de longueur avec limites

## 🔧 Prérequis

- **Java** : 21 LTS ou supérieur
- **Maven** : 3.9.11 ou supérieur
- **Docker** : (optionnel, pour containerisation)

## 📦 Installation

```bash
# Cloner le dépôt
git clone <repo-url>
cd SoftDep_Projet

# Compiler
mvn clean compile

# Tester
mvn test

# Créer le JAR
mvn package
```

## ▶️ Utilisation

### Exécution locale
```bash
java -jar target/stringvalidator.jar
```

### Exécution avec Docker
```bash
docker build -f DockerFile.build -t stringvalidator:java21 .
docker run --rm stringvalidator:java21
```

## 🧪 Tests

Le projet inclut 6 tests unitaires JUnit 5 :

- **Sûreté** (3 tests) : Validation de noms simples, composés, rejet de chiffres/symboles
- **Robustesse** (2 tests) : Gestion des valeurs null
- **Fiabilité** (3 tests) : Calcul de longueur, respect des limites, gestion du null

Lancer les tests :
```bash
mvn test
```

## 📝 Fonctionnalités

### `estNomValide(String nom)`
Valide un nom avec lettres (y compris accentuées) et espaces uniquement.

```java
StringValidator.estNomValide("Jean Dupont");    // true
StringValidator.estNomValide("Jean123");        // false
```

### `defaultIfNull(String str, String defaultStr)`
Retourne la chaîne ou une valeur par défaut si null.

```java
StringValidator.defaultIfNull(null, "défaut");  // "défaut"
StringValidator.defaultIfNull("valeur", "x");   // "valeur"
```

### `calculerLongueur(String str, int limiteMax)`
Calcule la longueur et vérifie la limite.

```java
StringValidator.calculerLongueur("test", 10);   // 4
StringValidator.calculerLongueur("trop long", 5); // Lance IllegalArgumentException
```

## 🐳 Docker

**DockerFile.build** : Utilise Java 21 JRE, exécute le JAR pré-compilé.

```dockerfile
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/stringvalidator.jar /app/stringvalidator.jar
CMD ["java", "-jar", "/app/stringvalidator.jar"]
```

## 📂 Structure du projet

```
src/
├── main/java/StringValidator.java         # Code principal
└── test/java/TestStringValidator.java     # Tests JUnit 5
pom.xml                                     # Configuration Maven
DockerFile.build                            # Configuration Docker
README.md                                   # Cette documentation
```

## ✅ État du projet

- ✅ Code corrigé (accents, orthographe)
- ✅ Tests unitaires (6 tests passants)
- ✅ Build Maven (Java 21)
- ✅ Containerisation Docker (Java 21)
- ✅ Documentation complète

## 📄 Licence

Projet éducatif - Sûreté Logicielle & Dépendances

## 👨‍💻 Auteur

Développé dans le contexte du cours de Sûreté Logicielle et Gestion des Dépendances.
