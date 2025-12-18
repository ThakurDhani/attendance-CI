def call(String channel) {

    slackSend channel: channel,
    message: "Attendance CI Pipeline Completed",
    tokenCredentialId: "slack-token"
}
