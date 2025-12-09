# Configuration des Secrets GitHub pour les Workflows de Sécurité

Ce document décrit comment configurer les secrets GitHub nécessaires pour que les workflows de sécurité fonctionnent correctement.

## Workflows à Configurer

### 1. **Security Scanning Workflow** (`security.yml`)
   - Snyk: Détection des vulnérabilités dans les dépendances
   - GitGuardian: Détection des secrets exposés
   - Dependency-Check: Analyse des risques de sécurité

### 2. **SonarQube Workflow** (`sonarqube.yml`)
   - SonarCloud: Analyse de qualité du code

## Étapes de Configuration

### Étape 1: Créer un compte Snyk
1. Aller à https://snyk.io et créer un compte gratuit
2. Se connecter et aller à Settings → API Token
3. Copier l'API token (commençant par `snyk-`)
4. Garder le token précieusement

### Étape 2: Générer un token GitGuardian
1. Aller à https://dashboard.gitguardian.com
2. S'enregistrer ou se connecter
3. Aller à Settings → API Tokens
4. Cliquer sur "Generate API Token"
5. Copier le token généré

### Étape 3: Créer un token SonarQube Cloud
1. Aller à https://sonarcloud.io
2. Se connecter avec GitHub (recommandé)
3. Aller à My Account → Security
4. Générer un nouveau token
5. Copier le token généré

### Étape 4: Configurer les Secrets dans GitHub
1. Aller au repository: https://github.com/kimou9/dependability-project-final
2. Settings → Secrets and variables → Actions
3. Ajouter les secrets suivants:

#### Secret 1: SNYK_TOKEN
- **Name**: `SNYK_TOKEN`
- **Value**: [Copier le token Snyk de l'étape 1]
- Cliquer "Add secret"

#### Secret 2: GITGUARDIAN_API_KEY
- **Name**: `GITGUARDIAN_API_KEY`
- **Value**: [Copier le token GitGuardian de l'étape 2]
- Cliquer "Add secret"

#### Secret 3: SONAR_TOKEN
- **Name**: `SONAR_TOKEN`
- **Value**: [Copier le token SonarQube de l'étape 3]
- Cliquer "Add secret"

## Vérifier les Secrets Configurés

Après avoir ajouté les secrets:
1. Allez à Settings → Secrets and variables → Actions
2. Vérifiez que les 3 secrets apparaissent (sans afficher leurs valeurs)
3. Les secrets seront maintenant disponibles dans les workflows

## Déclencher les Workflows

Une fois les secrets configurés, les workflows se déclencheront automatiquement sur:
- **Maven workflow**: Chaque push ou pull request
- **SonarQube workflow**: Chaque push sur main (si secrets configurés)
- **Security workflow**: Chaque push (si secrets configurés)
- **Docker workflow**: Chaque tag de release

Vous pouvez aussi déclencher manuellement:
1. Aller à Actions
2. Sélectionner le workflow
3. Cliquer "Run workflow"

## Résultats Attendus

### Maven Build
- Build succède avec 34 tests passants
- Rapport JaCoCo généré avec couverture ~75%
- Mutations PiTest analysées

### SonarQube
- Tableau de bord de qualité du code
- Détection des code smells, bugs, vulnérabilités
- Couverture de code visible

### Security
- Rapport Snyk des vulnérabilités
- Détection des secrets exposés par GitGuardian
- Dépendances dangereuses identifiées

### Docker
- Image Docker construite avec succès
- Tests exécutés dans le conteneur
- Prêt pour le déploiement

## Dépannage

### Le workflow fail avec "Secret not found"
- Vérifiez que le secret est bien ajouté (Settings → Secrets)
- Vérifiez l'orthographe exacte du nom du secret
- Les secrets sont sensibles à la casse

### Snyk dit "Unauthorized"
- Vérifiez que le token SNYK_TOKEN est correct
- Allez sur https://app.snyk.io/account/settings pour régénérer

### SonarQube dit "Unauthorized"
- Vérifiez que SONAR_TOKEN est correct
- Régénérez le token sur https://sonarcloud.io/account/security

## Documentation Additionnelle

- [Snyk Documentation](https://docs.snyk.io/)
- [GitGuardian Documentation](https://docs.gitguardian.com/)
- [SonarCloud Documentation](https://docs.sonarcloud.io/)
- [GitHub Actions Secrets](https://docs.github.com/en/actions/security-guides/using-secrets-in-github-actions)
