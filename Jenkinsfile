#!groovy
import java.text.SimpleDateFormat

def IMAGE_NAME = ''
dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
TAG = dateFormat.format(new Date())
def STAGING_TAG = 'staging-' + TAG

node {
    def mvnHome;
    def PWD = pwd();
    stage('Preparation') { // for display purposes
        // Get some code from a GitHub repository
        git 'https://github.com/sengkai/friendManagement.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
    }

    stage('Build Configuration'){
        pom = readMavenPom file: 'pom.xml'
        IMAGE_NAME = pom.build.finalName
        echo message: 'image: ' + IMAGE_NAME
    }

    stage('Maven Build') {
        // Run the maven build
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
            echo 'run here!'
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
        }
        echo PWD
        sh 'cp target/*.jar src/main/docker'
    }
/*
    stage('Build Docker'){

        //mvn clean package docker:build
        // Run the maven build
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' clean package docker:build"
        } else {
            bat(/"${mvnHome}\bin\mvn" clean package docker:build/)
        }
    }*/

    stage('Docker Image Build') {
        dir("src/main/docker") {

            echo PWD
            sh 'ls'
            sh 'pwd'
            sh 'docker build -t  ' + IMAGE_NAME + ' . '
            sh 'docker push ' + IMAGE_NAME
            /* bat 'docker build -t  ' + IMAGE_NAME + ' . '
             bat 'docker push ' + IMAGE_NAME*/
        }
    }

    stage('Results') {
        junit '**/target/surefire-reports/TEST-*.xml'
        archive 'target/*.jar'
    }
}