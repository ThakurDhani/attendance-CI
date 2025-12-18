def call() {
    sh '''
        echo "Running Dependency Scan..."
        pip install pip-audit
        pip-audit || true
    '''
}
