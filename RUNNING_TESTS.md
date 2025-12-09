# Exécution des Benchmarks JMH

## Méthode 1 : Via Maven directement (Recommandé)

Les benchmarks sont compilés et prêts à être exécutés. Utilisez la commande Maven :

```bash
# Compiler et exécuter les benchmarks
mvn clean package
mvn exec:java -Dexec.mainClass="com.baeldung.benchmarks.StringValidatorBenchmark"
mvn exec:java -Dexec.mainClass="com.baeldung.benchmarks.CollectionUtilsBenchmark"
```

## Méthode 2 : Exécution du JAR des benchmarks

```bash
# Compiler
mvn clean package

# Exécuter avec JMH runner
java -cp "target/benchmarks.jar:target/classes:target/test-classes" \
     org.openjdk.jmh.Main \
     com.baeldung.benchmarks.StringValidatorBenchmark

# Ou (si utilisant le shade plugin correctement configuré)
java -jar target/benchmarks.jar
```

## Méthode 3 : Configuration avec profil Maven

Pour créer un profil de benchmark optimisé, ajoutez le JMH Maven Plugin :

```xml
<profile>
    <id>benchmarks</id>
    <build>
        <plugins>
            <plugin>
                <groupId>org.openjdk.jmh</groupId>
                <artifactId>jmh-maven-plugin</artifactId>
                <version>1.36</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>benchmark</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <includes>
                        <include>com.baeldung.benchmarks.*Benchmark</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</profile>
```

Puis exécuter :
```bash
mvn clean package -P benchmarks
```

## Benchmarks Disponibles

### StringValidatorBenchmark
Mesure les performances de validation de chaînes :
- `benchmarkRegexMatchValid()` - Validation regex avec nom valide (~1000 ns)
- `benchmarkRegexMatchInvalid()` - Validation regex avec nom invalide (~1000 ns)
- `benchmarkDefaultIfNullNonNull()` - Remplacement null (non-null) (~100 ns)
- `benchmarkDefaultIfNullNull()` - Remplacement null (null) (~100 ns)
- `benchmarkCalculerLongueurValid()` - Calcul de longueur (~50-100 ns)
- `benchmarkPatternCompilation()` - Compilation de regex (~1000-5000 ns) ⚠️ Opération coûteuse

### CollectionUtilsBenchmark
Mesure les performances d'opérations génériques :
- `benchmarkPrintGeneric()` - Impression générique (~100 ns)
- `benchmarkSwap()` - Échange d'éléments (~1000 ns)
- `benchmarkMergeTypeParameter()` - Fusion avec type borné (~5000-10000 ns)
- `benchmarkMergeWildcard()` - Fusion avec wildcard (~5000-10000 ns)
- `benchmarkSum()` - Sommation sur Number (~10000 ns)
- `benchmarkSumTypeParameter()` - Sommation avec type borné (~10000 ns)
- `benchmarkSumWildcard()` - Sommation avec wildcard (~10000 ns)
- `benchmarkAddNumber()` - Ajout avec super wildcard (~100 ns)

## Options d'Exécution

```bash
# Warmup iterations: prepare JIT compiler
-wi 5

# Measurement iterations: actual benchmark runs
-i 10

# Number of forks (separate JVM processes)
-f 3

# Thread count
-t 1

# Timeout per iteration (in seconds)
-to 10

# Affichage verbose
-v

# Example: Full benchmark with 5 warmup, 10 measurement, 3 forks
java -cp "target/classes" -Xms1g -Xmx1g \
     org.openjdk.jmh.Main \
     -wi 5 -i 10 -f 3 \
     "com.baeldung.benchmarks.*"
```

## Résultats Attendus

```
Benchmark                                          Mode  Cnt        Score        Error  Units
StringValidatorBenchmark.benchmarkRegexMatchValid  avgt   10     1234.567 ±     45.123  ns/op
StringValidatorBenchmark.benchmarkDefaultIfNullNonNull  avgt   10      123.456 ±      5.123  ns/op
CollectionUtilsBenchmark.benchmarkSum              avgt   10    15000.000 ±   1000.000  ns/op
```

**Interprétation** :
- Score: Temps moyen par opération (nanosecondes)
- Error: Marge d'erreur (écart-type)
- Units: ns/op = nanosecondes par opération

## Optimisations JVM Recommandées

```bash
# Mode serveur (par défaut)
java -server -jar target/benchmarks.jar

# Avec pré-compilation
java -XX:+TieredCompilation -XX:TieredStopAtLevel=4

# Avec profiling
java -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading

# Performance maximum
java -server -Xms2g -Xmx2g -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     org.openjdk.jmh.Main
```

## Dépannage

### JAR non trouvé
```
Error: Could not find or load main class org.openjdk.jmh.Main
```
Solution: Recompiler avec `mvn clean package`

### Pas de benchmarks trouvés
```
Benchmark is missing @Benchmark annotation
```
Solution: Vérifier que les méthodes sont bien annotées avec `@Benchmark`

### Performance inattendue
- Exécuter avec plus de warmup iterations (`-wi 10`)
- Utiliser plus de forks (`-f 3`)
- Vérifier que d'autres processus ne consomment pas de ressources

## Intégration CI/CD

Dans GitHub Actions :
```yaml
- name: Run JMH Benchmarks
  run: mvn clean package
       mvn exec:java -Dexec.mainClass="com.baeldung.benchmarks.StringValidatorBenchmark"
```

Ou directement avec le JAR :
```yaml
- name: Run Benchmarks
  run: |
    mvn package
    java -cp target/classes:target/dependency/* \
         org.openjdk.jmh.Main \
         com.baeldung.benchmarks.*
```

## Ressources

- [JMH Documentation](https://github.com/openjdk/jmh)
- [JMH Maven Plugin](https://github.com/openjdk/jmh/tree/master/jmh-maven-plugin)
- [JMH Samples](https://github.com/openjdk/jmh/tree/master/jmh-samples)
