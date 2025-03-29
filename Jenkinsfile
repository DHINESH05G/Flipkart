pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'flipkart-app'  // Replace with your actual Docker Hub repo
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'  // Ensure this matches Jenkins credentials
        GIT_CREDENTIALS_ID = 'github-credentials'  // Ensure you have added this to Jenkins
    }

    stages {
        stage('Clone Repo') {
            steps {
                git branch: 'main', credentialsId: GIT_CREDENTIALS_ID, url: 'https://github.com/DHINESH05G/flipkart.git'
            }
        }

        stage('Build') {
            steps {
                sh 'gradle build'  // Ensure Gradle Wrapper is executable
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    withDockerRegistry([credentialsId: DOCKER_CREDENTIALS_ID, url: 'https://index.docker.io/v1/']) {
                        sh "docker build -t $DOCKER_IMAGE ."
                        sh "docker push $DOCKER_IMAGE:latest"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Stop and remove any running container
                    sh 'docker stop flipkart-app || true'
                    sh 'docker rm flipkart-app || true'

                    // Run the new container
                    sh "docker run -d --name flipkart-app -p 9090:8080 $DOCKER_IMAGE:latest"
                }
            }
        }
    }
}
