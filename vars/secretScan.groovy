def call() {

    sh '''
        echo "Running Gitleaks..."

        if ! command -v gitleaks &> /dev/null
        then
            echo "Installing Gitleaks..."
            curl -sSfL https://raw.githubusercontent.com/gitleaks/gitleaks/master/install.sh | bash
        fi

        gitleaks detect \
        --source . \
        --report-format json \
        --report-path gitleaks-report.json \
        || true
    '''

    archiveArtifacts artifacts: 'gitleaks-report.json'
}
