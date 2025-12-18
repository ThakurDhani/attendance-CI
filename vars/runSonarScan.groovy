def call() {

 withSonarQubeEnv('SonarQube') {

    sh """
        ${tool 'sonar-scanner'}/bin/sonar-scanner \
        -Dsonar.projectKey=attendance-api \
        -Dsonar.sources=. \
        -Dsonar.python.coverage.reportPaths=coverage.xml || true
    """
 }
}
