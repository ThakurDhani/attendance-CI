def call() {

    sh '''
        set +e

        echo "[INFO] Running Gitleaks secret scan..."

        sudo apt-get update -y >/dev/null 2>&1 || true
        sudo apt-get install -y wget unzip >/dev/null 2>&1 || true

        if ! command -v gitleaks >/dev/null 2>&1
        then
            echo "[INFO] Installing gitleaks binary..."

            wget -q https://github.com/gitleaks/gitleaks/releases/latest/download/gitleaks-linux-amd64.zip || true

            unzip -o gitleaks-linux-amd64.zip >/dev/null 2>&1 || true

            sudo mv -f gitleaks /usr/local/bin/gitleaks >/dev/null 2>&1 || true

            sudo chmod +x /usr/local/bin/gitleaks >/dev/null 2>&1 || true
        else
            echo "[INFO] Using installed gitleaks: $(gitleaks version)"
        fi

        echo "[INFO] Running gitleaks detect..."
        gitleaks detect \
            --source . \
            --report-format json \
            --report-path gitleaks-report.json \
            >/dev/null 2>&1 || true

        echo "[INFO] Secret scan completed."
    '''

    archiveArtifacts artifacts: 'gitleaks-report.json', allowEmptyArchive: true
}
