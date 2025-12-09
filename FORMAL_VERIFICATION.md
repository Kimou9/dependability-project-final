# OpenJML Verification Guide

## Présentation

OpenJML (Java Modeling Language) est un outil de vérification formelle qui vérifie que le code respecte ses contrats JML spécifiés.

## Spécifications JML Utilisées

Le projet contient des spécifications JML dans les fichiers suivants:

### 1. StringValidator.java
```java
//@ requires name != null;
//@ ensures \result == name.matches(...);
public static boolean estNomValide(String name)
```

### 2. Animal.java
```java
//@ requires name != null && name.length() > 0;
//@ ensures this.name.equals(name);
//@ invariant name != null && name.length() > 0;
public abstract class Animal
```

### 3. Cat.java
```java
//@ requires that instanceof Cat;
//@ ensures \result > 0 implies true;
public int compareTo(Cat that)
```

### 4. CollectionUtils.java
```java
//@ requires collection != null;
//@ ensures \result == null || collection.contains(\result);
public static <T> Optional<T> findElement(Collection<T> collection, T target)
```

### 5. PizzaStatus.java
```java
//@ requires statusCode >= 0;
//@ ensures \result != null;
public static PizzaStatus delivery(int statusCode)
```

## Configuration

### Dépendances Maven

Le pom.xml contient la configuration OpenJML:
```xml
<plugin>
    <groupId>org.openjml</groupId>
    <artifactId>openjml-maven-plugin</artifactId>
    <version>0.10.6</version>
</plugin>
```

### Profil de Vérification Formelle

Un profil spécifique pour la vérification:
```bash
mvn clean verify -P formal-verification
```

## Exécution

### Vérification Basique
```bash
# Vérifier tous les fichiers
mvn clean verify

# Mode verbeux pour voir les détails
mvn clean verify -X
```

### Vérification d'un Fichier Spécifique
```bash
# Via Maven
mvn org.openjml:openjml-maven-plugin:check

# Avec profil formel
mvn clean verify -P formal-verification
```

### Options de Configuration

Dans le pom.xml:
```xml
<configuration>
    <javaVersion>21</javaVersion>
    <verbose>false</verbose>
    <sourceLanguage>jml</sourceLanguage>
    <sourcePaths>
        <sourcePathItem>src/main/java</sourcePathItem>
    </sourcePaths>
</configuration>
```

## Spécifications JML Expliquées

### 1. @requires (Précondition)
Condition qui doit être vraie avant l'exécution de la méthode:
```java
//@ requires name != null;
public boolean estNomValide(String name)
```
Signifie: Le paramètre `name` ne doit jamais être null quand la méthode est appelée.

### 2. @ensures (Postcondition)
Condition qui doit être vraie après l'exécution:
```java
//@ ensures \result >= 0;
public int calculerLongueur(String str, int limit)
```
Signifie: La méthode retourne toujours un entier ≥ 0.

### 3. @invariant (Invariant de Classe)
Propriété qui doit toujours rester vraie:
```java
//@ invariant name != null && name.length() > 0;
public abstract class Animal
```
Signifie: L'attribut `name` ne peut jamais être null ni vide pour toute instance de Animal.

### 4. \result
Représente la valeur de retour de la méthode:
```java
//@ ensures \result == input.toUpperCase();
```

### 5. \old()
Valeur d'une variable avant l'exécution:
```java
//@ ensures count == \old(count) + 1;
```

## Types de Vérification

### 1. Vérification de Contrats
- OpenJML vérifie que chaque appel respecte les @requires
- Vérifie que chaque méthode produit les @ensures déclarés
- Vérifie que les @invariant sont maintenus

### 2. Vérification Statique vs Dynamique
- **Statique**: OpenJML détecte les violations à la compilation
- **Dynamique**: Assertions runtime pour vérifier à l'exécution

## Résultats Attendus

### Succès
```
[INFO] JML verification completed successfully
[INFO] All contracts verified
```

### Avertissements
```
[WARNING] Method estNomValide: precondition may not hold
         (parameter 'name' may be null)
```

### Erreurs
```
[ERROR] Class Animal: invariant violation possible
        (field 'name' could be null)
```

## Fichiers JML dans le Projet

Les spécifications JML sont directement dans les sources:

1. **src/main/java/StringValidator.java**
   - Spécifications pour validation
   - 3 contrats JML

2. **src/main/java/com/baeldung/generics/Animal.java**
   - Classe abstraite avec invariants
   - 2 contrats JML

3. **src/main/java/com/baeldung/generics/Cat.java**
   - Implémentation Comparable
   - 1 contrat JML

4. **src/main/java/com/baeldung/generics/CollectionUtils.java**
   - Utilitaires génériques avec contrats
   - 4+ contrats JML

5. **src/main/java/com/baeldung/inttoenum/PizzaStatus.java**
   - Énumération avec contrats
   - 2 contrats JML

## Workflow d'Intégration Continue

Le fichier `.github/workflows/maven.yml` inclut la vérification OpenJML:
```yaml
- name: Run OpenJML verification
  run: mvn clean verify -P formal-verification
```

## Interprétation des Résultats

### Aucune Erreur = ✅
Le code respecte tous les contrats spécifiés.

### Avertissements = ⚠️
OpenJML a détecté des cas possibles où un contrat pourrait être violé.
À investiguer et corriger.

### Erreurs = ❌
Violation confirmée d'un contrat.
Doit être corrigée immédiatement.

## Dépannage

### Erreur: "OpenJML not found"
- Vérifier que maven-compiler est configuré pour Java 21
- Vérifier que le plugin OpenJML est dans pom.xml

### Erreur: "Invalid JML syntax"
- Vérifier la syntaxe JML (sensible à la casse)
- Utiliser `\result` et non `result`
- Ajouter `;` à la fin des spécifications

### Avertissement: "Precondition may not hold"
- Vérifier que tous les appels passent des paramètres valides
- Ajouter des vérifications null si nécessaire

## Ressources

- [JML Reference Manual](http://www.openjml.org/)
- [OpenJML GitHub](https://github.com/OpenJML/OpenJML)
- [JML by Example](http://www.lsv.fr/~comon/Recherche/Seminaires/Jml.pdf)
- [Formal Verification Techniques](https://en.wikipedia.org/wiki/Formal_verification)

## Bonnes Pratiques

1. **Commencez simple**: Ajoutez progressivement les contrats
2. **Utilisez \old()**: Pour tracker les changements d'état
3. **Testez vos contrats**: Assurez-vous qu'ils sont corrects
4. **Documentez**: Expliquez pourquoi les contrats existent
5. **Maintenez les contrats**: Quand le code change, mettez à jour les contrats

## Exemple Complet

```java
/**
 * Valide un nom selon les règles métier.
 * 
 * @param name le nom à valider (non-null)
 * @return true si le nom est valide, false sinon
 * 
 * JML Contracts:
 * - Précondition: name doit être non-null
 * - Postcondition: retourne true si name contient uniquement des lettres
 * - Invariant: (implicite) aucun effet de bord
 */
//@ requires name != null;
//@ ensures \result == name.matches("\\p{L}+");
public static boolean estNomValide(String name) {
    return name.matches("\\p{L}+");
}
```

Ce contrat dit: "Si on passe un nom non-null, la méthode retourne true si et seulement si ce nom ne contient que des lettres Unicode."
