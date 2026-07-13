pipeline {
    agent any

    tools {
        maven 'Maven3'   // Name must match a Maven installation configured in Jenkins > Global Tool Configuration
        jdk 'JDK21'      // Name must match a JDK installation configured in Jenkins > Global Tool Configuration
    }

    environment {
    DOCKER_IMAGE = "ott-platform"
    IMAGE_TAG = "${env.BUILD_NUMBER}"
}

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn -B test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging application as JAR...'
                sh 'mvn -B package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh "docker build -t ${DOCKER_IMAGE}:${IMAGE_TAG} -t ${DOCKER_IMAGE}:latest ."
            }
        }

        

        stage('Deploy') {
    steps {
        echo 'Deploying with docker-compose...'
        sh 'docker compose down -v || true'
        sh 'docker rm -f ott-mysql ott-platform-app || true'
        sh 'docker compose up -d'
    }
}
    }

    post {
    success {
        echo 'Pipeline completed successfully!'
    }
    failure {
        echo 'Pipeline failed. Check the logs above.'
    }
    always {
        echo 'Pipeline finished'
    }
}
}
