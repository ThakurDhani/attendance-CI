def call() {
    sh '''
        echo "Running License Scan..."
        pip install pip-licenses
        pip-licenses --format=markdown > license-report.md
    '''
    archiveArtifacts artifacts: 'license-report.md'
}
