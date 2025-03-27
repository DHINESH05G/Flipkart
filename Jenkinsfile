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
                sh 'gradle clean build'
            }
        }

        stage('Docker Build & Push') {
            steps {
                withDockerRegistry([credentialsId:docker-hub-credentials, url: 'https://index.docker.io/v1/']) {
                    
                }
                    sh 'docker build -t $DOCKER_IMAGE .'
                    sh 'docker push $DOCKER_IMAGE:latest'
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
                    sh 'docker run -d --name shopping-app -p 9090:8080 $DOCKER_IMAGE:latest'
                }
            }
        }
    }
}
