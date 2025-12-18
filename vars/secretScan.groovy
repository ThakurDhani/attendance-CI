def call() {

    sh '''
        echo "Running Gitleaks..."

        # Install tools required
        sudo apt-get update -y >/dev/null
        sudo apt-get install -y wget unzip >/dev/null

        if ! command -v gitleaks &> /dev/null
        then
            echo "Installing gitleaks..."

            wget -q https://github.com/gitleaks/gitleaks/releases/latest/download/gitleaks-linux-amd64.zip

            unzip -o gitleaks-linux-amd64.zip > /dev/null

            sudo mv gitleaks /usr/local/bin/gitleaks

            sudo chmod +x /usr/local/bin/gitleaks

            echo "GitLeaks installed -> $(gitleaks version)"
        else
            echo "GitLeaks already installed -> $(gitleaks version)"
        fi

        echo "Executing Gitleaks scan..."

        gitleaks detect \
            --source . \
            --report-format json \
            --report-path gitleaks-report.json \
            || echo "GitLeaks returned findings or exit code - continuing pipeline"
    '''

    archiveArtifacts artifacts: 'gitleaks-report.json', allowEmptyArchive: true
}
