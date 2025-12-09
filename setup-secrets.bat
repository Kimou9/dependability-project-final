@echo off
REM Quick setup guide for GitHub Secrets on Windows
REM Instructions for configuring Snyk, GitGuardian, and SonarQube tokens

echo.
echo =========================================
echo GitHub Secrets Configuration Guide
echo =========================================
echo.
echo This script provides instructions for setting up GitHub secrets.
echo You need to manually create accounts and add secrets via GitHub UI.
echo.
echo ===== STEP 1: SNYK_TOKEN =====
echo 1. Go to: https://app.snyk.io/account/settings/targets
echo 2. Copy your API Token
echo 3. Open: https://github.com/Kimou9/dependability-project-final/settings/secrets/actions
echo 4. Click 'New repository secret'
echo 5. Name: SNYK_TOKEN
echo 6. Paste the token and save
echo.
echo ===== STEP 2: GITGUARDIAN_API_KEY =====
echo 1. Go to: https://dashboard.gitguardian.com/account/api-tokens
echo 2. Create a new API token
echo 3. Copy the token
echo 4. Open: GitHub Settings ^> Secrets ^> Actions
echo 5. Click 'New repository secret'
echo 6. Name: GITGUARDIAN_API_KEY
echo 7. Paste the token and save
echo.
echo ===== STEP 3: SONAR_TOKEN =====
echo 1. Go to: https://sonarcloud.io/account/security/
echo 2. Generate a new token (name it: github-actions)
echo 3. Copy the token
echo 4. Open: GitHub Settings ^> Secrets ^> Actions
echo 5. Click 'New repository secret'
echo 6. Name: SONAR_TOKEN
echo 7. Paste the token and save
echo.
echo ===== STEP 4: Verify Secrets =====
echo 1. Open: https://github.com/Kimou9/dependability-project-final/settings/secrets/actions
echo 2. You should see 3 secrets (SNYK_TOKEN, GITGUARDIAN_API_KEY, SONAR_TOKEN)
echo.
echo ===== STEP 5: Trigger Workflows =====
echo 1. Go to: https://github.com/Kimou9/dependability-project-final/actions
echo 2. Select the workflow you want to run
echo 3. Click 'Run workflow'
echo.
echo =========================================
echo.
pause
