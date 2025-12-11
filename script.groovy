def buildJar() {
    echo "building jar file"
    sh "mvn clean package"
}

def buildImage() {
    echo "building docker image"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
        sh "docker build -t slimboi/java-maven-app:jma-2.0 ."
        sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin"
        sh "docker push slimboi/java-maven-app:jma-2.0"
    }
}

def deployApp() {
    echo "deploying the application"
}

return this
