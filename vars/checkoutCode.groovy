def call(String url, String branch) {

    checkout([
        $class: 'GitSCM',
        branches: [[name: branch]],
        userRemoteConfigs: [[url: url]]
    ])
}
