def call() {

    sh '''
        set +e

        echo "Running Dependency Scan..."

        export PATH=$HOME/.local/bin:$PATH

        pip install pip-audit >/dev/null 2>&1 || true

        pip-audit || true
    '''
}
