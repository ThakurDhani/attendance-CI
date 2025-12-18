def call() {

    sh '''
        export PATH=/var/lib/jenkins/.local/bin:$PATH

        poetry config virtualenvs.create true
        poetry config virtualenvs.in-project true

        poetry install --no-root

        poetry run pytest \
        --junitxml=test-results.xml \
        --cov=. \
        --cov-report=xml
    '''
}
