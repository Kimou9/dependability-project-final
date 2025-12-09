# JMH Benchmarks Guide

## Présentation

Les benchmarks JMH (Java Microbenchmark Harness) mesurent les performances précises des méthodes critiques du projet.

## Benchmarks Disponibles

### 1. StringValidatorBenchmark
Mesure les performances de la validation des chaînes de caractères:
- `benchmarkEstNomValideValid()` - Validation d'un nom valide
- `benchmarkEstNomValideInvalid()` - Validation d'un nom invalide
- `benchmarkEstNomValideNull()` - Validation d'une valeur null
- `benchmarkDefaultIfNullNonNull()` - Remplacement par défaut (non-null)
- `benchmarkDefaultIfNullNull()` - Remplacement par défaut (null)
- `benchmarkCalculerLongueurValid()` - Calcul de longueur
- `benchmarkCalculerLongueurNull()` - Calcul de longueur avec null

### 2. CollectionUtilsBenchmark
Mesure les performances des opérations sur les collections génériques:
- `benchmarkFindElement()` - Recherche d'élément trouvé
- `benchmarkFindElementNotFound()` - Recherche d'élément non trouvé
- `benchmarkIsNullOrEmptyNonEmpty()` - Vérification liste non-vide
- `benchmarkIsNullOrEmptyEmpty()` - Vérification liste vide
- `benchmarkIsNullOrEmptyNull()` - Vérification null
- `benchmarkConvertToSet()` - Conversion liste en ensemble
- `benchmarkGetFirstElement()` - Obtention du premier élément
- `benchmarkFilterByType()` - Filtrage par type

## Configuration

Les benchmarks utilisent la configuration suivante:
- **Mode**: AverageTime (temps moyen par opération)
- **Unit**: Nanoseconds (ns)
- **Warmup**: 3 itérations pour stabiliser le JIT
- **Measurement**: 5 itérations de mesure
- **Forks**: 1 processus séparé pour chaque benchmark
- **Scope**: Benchmark (état réinitialisé entre les benchmarks)

## Exécution

### Via Maven:
```bash
# Compiler avec les benchmarks
mvn clean package

# Exécuter les benchmarks
java -jar target/benchmarks.jar

# Exécuter un benchmark spécifique
java -jar target/benchmarks.jar StringValidatorBenchmark

# Avec options avancées
java -jar target/benchmarks.jar StringValidatorBenchmark -wi 5 -i 10 -f 2
```

Où:
- `-wi` = nombre d'itérations de warmup
- `-i` = nombre d'itérations de mesure
- `-f` = nombre de forks

### Directement (si JMH est dans le classpath):
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.baeldung.benchmarks.StringValidatorBenchmark"
```

## Interprétation des Résultats

Exemple de sortie:
```
Benchmark                                   Mode  Cnt      Score      Error  Units
StringValidatorBenchmark.benchmarkEstNomValideValid  avgt    5   1234.567 ±   45.123  ns/op
```

Interprétation:
- **Score**: 1234.567 ns/op = chaque opération prend ~1.23 microsecondes
- **Error**: ±45.123 ns = marge d'erreur (variance)
- **Units**: ns/op = nanosecondes par opération

### Évaluation des Performances:
- < 100 ns: Très rapide (opération simple, quelques instructions CPU)
- 100-1000 ns: Rapide (opération légère)
- 1000-10000 ns: Acceptable (opération modérée)
- > 10000 ns: À investiguer (peut-être optimisable)

## Format de Rapport

JMH génère automatiquement:
```
# Run complete. Total time: 00:05:23

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof) to gain more insights...

Benchmark                                   Mode  Cnt         Score         Error  Units
StringValidatorBenchmark.benchmarkEstNomValideValid  avgt    5     1234.567 ±     45.123  ns/op
StringValidatorBenchmark.benchmarkEstNomValideInvalid  avgt    5     2345.678 ±     67.234  ns/op
...
```

## Optimisations et Profiling

Pour obtenir plus de détails:
```bash
# Avec profiler intégré
java -jar target/benchmarks.jar -prof jfr -prof gc

# Avec flamegraph
java -jar target/benchmarks.jar -prof jfr -prof flamegraph

# Exécution verbale
java -jar target/benchmarks.jar -v -wi 3 -i 5
```

## Intégration CI/CD

Les benchmarks peuvent être intégrés dans le pipeline GitHub Actions:
```yaml
- name: Run JMH Benchmarks
  run: |
    mvn clean package
    java -jar target/benchmarks.jar > benchmark-results.txt
    
- name: Upload Benchmark Results
  uses: actions/upload-artifact@v3
  with:
    name: jmh-benchmarks
    path: benchmark-results.txt
```

## Bonnes Pratiques

1. **Exécuter en isolation**: Fermez d'autres applications
2. **Utiliser le mode production**: Exécutez avec `-server` JVM flag
3. **Plusieurs forks**: Utilisez `-f 3` ou plus pour la stabilité
4. **Warmup approprié**: Laissez le JIT compiler le code
5. **Comparer les versions**: Benchmarkez avant et après optimisation

## Ressources

- [JMH Documentation](https://github.com/openjdk/jmh)
- [JMH Samples](https://github.com/openjdk/jmh/tree/master/jmh-samples)
- [JMH Tutorial](https://www.baeldung.com/java-microbenchmark-harness)
