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
- **JaCoCo** (0.8.10) : Couverture (50% threshold)
- **PiTest** (1.14.2) : Mutation testing (70%+)
- **JMH** (1.36) : Benchmarking de performance

## 📊 Qualité du Code

```bash
# Couverture JaCoCo
mvn jacoco:report
# Rapport: target/site/jacoco/index.html

# Mutation testing PiTest
mvn pitest:mutationCoverage
# Rapport: target/pit-reports/

# JMH Benchmarks
mvn clean package
java -jar target/benchmarks.jar
```

## 🔬 Vérification Formelle (JML/OpenJML)

```bash
# Vérification OpenJML
mvn clean verify

# Avec profil formel (verbose)
mvn clean verify -P formal-verification
```

**Spécifications JML présentes dans**:
- StringValidator.java (@requires, @ensures)
- Animal.java (@invariant)
- Cat.java, CollectionUtils.java, PizzaStatus.java

## ⚡ Performance & Benchmarks (JMH)

```bash
# Compiler avec benchmarks
mvn clean package

# Exécuter StringValidator benchmarks
java -jar target/benchmarks.jar StringValidatorBenchmark

# Exécuter CollectionUtils benchmarks
java -jar target/benchmarks.jar CollectionUtilsBenchmark

# Tous les benchmarks
java -jar target/benchmarks.jar
```

**Benchmarks inclus**:
- StringValidator: estNomValide, defaultIfNull, calculerLongueur
- CollectionUtils: findElement, isNullOrEmpty, convertToSet, filterByType

📊 Voir [JMH_BENCHMARKS.md](JMH_BENCHMARKS.md) pour guide complet

## 🔐 GitHub Workflows

| Workflow | Trigger | Action |
|----------|---------|--------|
| maven.yml | push/PR | Compile, test, coverage, OpenJML |
| sonarqube.yml | push/PR | SonarQube analysis |
| security.yml | push/PR/schedule | Snyk, GitGuardian, Dependency-Check |
| docker.yml | push/PR | Docker build & test |

**⚠️ Configuration requise**: Voir [GITHUB_SECRETS_SETUP.md](GITHUB_SECRETS_SETUP.md) pour configurer les secrets GitHub (SNYK_TOKEN, GITGUARDIAN_API_KEY, SONAR_TOKEN)

## 📚 Documentation Complète

- **[OPENJML_VERIFICATION.md](OPENJML_VERIFICATION.md)** - Vérification formelle avec JML
- **[JMH_BENCHMARKS.md](JMH_BENCHMARKS.md)** - Benchmarking de performance
- **[GITHUB_SECRETS_SETUP.md](GITHUB_SECRETS_SETUP.md)** - Configuration des workflows sécurité

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

## ✅ Checklist - Option 2 Complète

- [x] Java 21 LTS setup
- [x] Maven configuration
- [x] 34 tests JUnit 5 (all passing)
- [x] JaCoCo coverage analysis (50% threshold)
- [x] PiTest mutation testing (70% threshold)
- [x] GitHub Workflows (4 workflows)
- [x] OpenJML formal verification (JML specs)
- [x] JMH benchmarks (StringValidator + CollectionUtils)
- [x] SonarQube ready (secrets config in progress)
- [x] Snyk integration ready (secrets config in progress)
- [x] GitGuardian setup ready (secrets config in progress)
- [x] Docker ready (build & multi-stage)

## 🔗 Ressources

- **GitHub** : https://github.com/Kimou9/dependability-project-final
- **Baeldung** : https://github.com/eugenp/tutorials
- **Maven Central** : https://mvnrepository.com/
- **OpenJML** : http://www.openjml.org/
- **JMH** : https://github.com/openjdk/jmh

## 📝 Licence

MIT License

---

**Status** : Phase 4 complétée - Option 2 (JMH + OpenJML + Workflows complets)  
**Dernière MAJ** : Décembre 2025  
**Test Coverage** : 34/34 passing | JaCoCo: 50% | PiTest: 70% | Docker: Ready | JML: Active | JMH: 15+ benchmarks


