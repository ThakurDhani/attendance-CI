def call() {

    sh '''
        set +e

        echo "Running License Scan..."

        export PATH=$HOME/.local/bin:$PATH

        pip install pip-licenses >/dev/null 2>&1 || true

        pip-licenses --format=markdown > license-report.md || true
    '''

    archiveArtifacts artifacts: 'license-report.md', allowEmptyArchive: true
}
