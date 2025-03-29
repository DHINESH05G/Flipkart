pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'dhinesh05g/flipkart-app'  // Replace with your Docker Hub repo
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'  // Ensure this is added in Jenkins
        GIT_CREDENTIALS_ID = 'github-credentials'  // Ensure this is added in Jenkins
    }

    stages {
        stage('Clone Repo') {
            steps {
                git branch: 'main', credentialsId: 'github-credentials', url: 'https://github.com/DHINESH05G/flipkart.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'  // Use 'bat' for Windows
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    withDockerRegistry([credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/']) {
                        sh "docker build -t $DOCKER_IMAGE ."
                        sh "docker push $DOCKER_IMAGE:latest"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker stop flipkart-app || true'
                    sh 'docker rm flipkart-app || true'
                    sh "docker run -d --name flipkart-app -p 9090:8080 $DOCKER_IMAGE:latest"
                }
            }
        }
    }
}
