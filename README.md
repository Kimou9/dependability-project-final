# StringValidator & Baeldung Generics - Projet Dépendabilité

**Projet académique de génie logiciel avec sûreté, robustesse et fiabilité.**

## 📋 Vue d'ensemble

Ce projet démontre les principes fondamentaux du développement logiciel dépendable en Java 21 LTS, intégrant:
- **Validation de chaînes de caractères** (sûreté, robustesse, fiabilité)
- **Programmation générique** (types bornés, wildcards)
- **Enums et conversion d'entiers**
- **Patterns de gestion d'exceptions** (finally keyword)
- **Assurance qualité** : JUnit 5, JaCoCo (couverture), PiTest (mutation testing)
- **Intégration continue** : GitHub Workflows, SonarQube, sécurité (Snyk, GitGuardian)
- **Containerisation** : Docker avec Java 21 LTS

## 🎯 Objectifs du Projet

### 1. **Sûreté** (Security)
- Validation stricte des entrées avec regex Unicode
- Gestion sécurisée des valeurs null
- Tests unitaires exhaustifs couvrant les edge cases

### 2. **Robustesse** (Robustness)
- Gestion d'exceptions appropriées
- Patterns finally keyword pour garantir l'exécution
- Conversion sûre int→enum avec validation

### 3. **Fiabilité** (Reliability)
- Couverture de code JaCoCo (target: 70%+)
- Mutation testing avec PiTest
- Tests d'intégration pour scénarios complexes
- Vérification formelle avec JML (spécifications en commentaires)

## 📦 Structure du Projet

```
SoftDep_Projet/
├── src/
│   ├── main/java/
│   │   ├── StringValidator.java                 # Validation de chaînes
│   │   └── com/baeldung/
│   │       ├── generics/
│   │       │   ├── Animal.java                 # Classe abstraite
│   │       │   ├── Cat.java                    # Implémente Comparable
│   │       │   ├── Dog.java                    # Implémentation concrète
│   │       │   └── CollectionUtils.java        # Méthodes génériques
│   │       ├── inttoenum/
│   │       │   └── PizzaStatus.java            # Enum int-to-enum
│   │       └── finallykeyword/
│   │           ├── FinallyExample.java         # Pattern try-finally
│   │           └── FinallyExecutedCases.java   # 5 scénarios
│   └── test/java/
│       ├── TestStringValidator.java             # 6 tests
│       └── com/baeldung/
│           ├── generics/*Test.java              # 11 tests
│           ├── inttoenum/*Test.java             # 11 tests
│           └── finallykeyword/*Test.java        # 6 tests
├── .github/workflows/
│   ├── maven.yml                                # Build & Test
│   ├── sonarqube.yml                            # Analyse qualité
│   ├── security.yml                             # Scan sécurité
│   └── docker.yml                               # Docker build
├── pom.xml                                      # Configuration Maven
├── DockerFile.build                             # Containerisation
└── README.md                                    # Ce fichier
```

## 🛠️ Prérequis

- **Java** : JDK 21 LTS
- **Maven** : 3.9.11+
- **Docker** : (optionnel)
- **Git** : Pour version control

## 🚀 Installation & Utilisation

### Installation

```bash
# Cloner
git clone https://github.com/Kimou9/dependability-project-final.git
cd SoftDep_Projet

# Compiler
mvn clean compile

# Tests
mvn test                    # Tests unitaires
mvn verify                  # Tests + intégration + JaCoCo

# Package
mvn package
```

### Exécution

```bash
java -jar target/stringvalidator.jar
```

### Docker

```bash
docker build -f DockerFile.build -t softdep:java21 .
docker run --rm softdep:java21
```

## 🧪 Tests (34 tests)

| Module | Unit | Integration | Total |
|--------|------|-------------|-------|
| StringValidator | 6 | - | 6 |
| Generics | 7 | 4 | 11 |
| Enums | 7 | 4 | 11 |
| Finally | 6 | - | 6 |
| **Total** | **26** | **8** | **34** |

### Test Frameworks
- **JUnit 5** (5.9.3) : Framework principal
- **Mockito** (5.2.0) : Mocking
- **JaCoCo** (0.8.10) : Couverture (70%+)
- **PiTest** (1.14.2) : Mutation testing (70%+)

## 📊 Qualité du Code

```bash
# Couverture JaCoCo
mvn jacoco:report
# Rapport: target/site/jacoco/index.html

# Mutation testing PiTest
mvn pitest:mutationCoverage
# Rapport: target/pit-reports/
```

## 🔐 GitHub Workflows

| Workflow | Trigger | Action |
|----------|---------|--------|
| maven.yml | push/PR | Compile, test, coverage |
| sonarqube.yml | push/PR | SonarQube analysis |
| security.yml | push/PR/schedule | Snyk, GitGuardian |
| docker.yml | push/PR | Docker build & test |

## 📋 Modules Détaillés

### StringValidator
- `estNomValide()` : Valide noms (Unicode + espaces)
- `defaultIfNull()` : Gestion null
- `calculerLongueur()` : Validation avec limite
- **Regex** : `^[\p{L}\s]+$` (lettres Unicode)

### Generics
- **Animal** : Classe abstraite avec JML
- **Cat** : Implements Comparable<Cat>
- **Dog** : Implémentation simple
- **CollectionUtils** : 8 méthodes génériques (swap, merge, sum)

### Enum
- **PizzaStatus** : ORDERED(5), READY(2), DELIVERED(0)
- Conversion int→enum via HashMap
- Edge case handling

### Finally
- Normal flow
- Exceptions unhandled/handled
- Return from try/catch
- Propagation d'exceptions

## ✅ Checklist

- [x] Java 21 LTS setup
- [x] Maven configuration
- [x] 34 tests JUnit 5
- [x] JaCoCo coverage
- [x] PiTest mutation
- [x] GitHub Workflows
- [ ] SonarQube Cloud
- [ ] Snyk integration
- [ ] GitGuardian setup
- [ ] OpenJML verification

## 🔗 Ressources

- **GitHub** : https://github.com/Kimou9/dependability-project-final
- **Baeldung** : https://github.com/eugenp/tutorials
- **Maven Central** : https://mvnrepository.com/

## 📝 Licence

MIT License

---

**Status** : Phase 3 complétée (GitHub Workflows, 34 tests passant)  
**Dernière MAJ** : Décembre 2025
