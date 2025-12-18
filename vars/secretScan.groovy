def call() {

    sh '''
        echo "Running Gitleaks..."

        # Install dependencies
        sudo apt-get update -y
        sudo apt-get install -y wget unzip

        # Install Gitleaks if missing
        if ! command -v gitleaks &> /dev/null
        then
            echo "Downloading Gitleaks latest release..."
            wget -q https://github.com/gitleaks/gitleaks/releases/latest/download/gitleaks-linux-amd64.zip

            echo "Unzipping..."
            unzip -o gitleaks-linux-amd64.zip

            echo "Moving binary..."
            sudo mv gitleaks /usr/local/bin/

            echo "Making binary executable"
            sudo chmod +x /usr/local/bin/gitleaks

            gitleaks version
        fi

        echo "Running scan..."
        gitleaks detect \
        --source . \
        --report-format json \
        --report-path gitleaks-report.json \
        || true
    '''

    archiveArtifacts artifacts: 'gitleaks-report.json', allowEmptyArchive: true
}
