def call(String sonarServer) {

    withSonarQubeEnv("${sonarServer}") {

        sh """
            sonar-scanner \
            -Dsonar.projectKey=attendance-api \
            -Dsonar.sources=. \
            -Dsonar.python.coverage.reportPaths=coverage.xml
        """
    }
}
