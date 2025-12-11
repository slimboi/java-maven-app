pipeline {   
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("build jar") {
            steps {
                script {
                    echo "building jar file"
                    sh "mvn clean package"
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh "docker build -t slimboi/java-maven-app:jma-2.0 ."
                        sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin"
                        sh "docker push slimboi/java-maven-app:jma-2.0 ."
                    }
                
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                   echo "deploying the application"
                }
            }
        }               
    }
} 
