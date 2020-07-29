pipeline {
    agent any
    stages {
        stage('Run') {
            steps {
                bat 'java -jar target/startWiremock-0.1-jar-with-dependencies.jar'
            }
        }
    }
}
