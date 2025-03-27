pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'flipkart-app'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
    }

    stages {
        stage('Clone Repo') {
            steps {
                git credentialsId: 'github-credentials', url: 'https://github.com/DHINESH05G/flipkart.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    withDockerRegistry([credentialsId: DOCKER_CREDENTIALS_ID, url: 'https://index.docker.io/v1/']) {
                        sh 'docker build -t $DOCKER_IMAGE .'
                        sh 'docker tag $DOCKER_IMAGE:latest your-docker-hub/$DOCKER_IMAGE:latest'
                        sh 'docker push your-docker-hub/$DOCKER_IMAGE:latest'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Stop and remove any existing container
                    sh 'docker stop flipkart-app || true'
                    sh 'docker rm flipkart-app || true'

                    // Run the new container
                    sh 'docker run -d --name flipkart-app --network flipkart-network -p 9090:8080 your-docker-hub/$DOCKER_IMAGE:latest'
                }
            }
        }
    }
}
