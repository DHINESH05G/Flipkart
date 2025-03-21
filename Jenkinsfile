pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat './gradlew build'
            }
        }
        stage('Docker Build & Push') {
            steps {
                bat 'docker build -t DOCKER.IO/LIBRARY/FLIPKART-APP:LATEST .'
                bat 'docker push DOCKER.IO/LIBRARY/FLIPKART-APP:LATEST'
            }
        }
        stage('Deploy') {
            steps {
                bat 'docker run -d -p 8080:8080 your-docker-hub/shopping-app:latest'
            }
        }
    }
}
