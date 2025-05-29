pipeline {
    agent any
    stages {
        stage('git_pull') {
            steps {
                echo "pull success"
            }
        }
        stage('build') {
            steps {
                 echo "build success"
            }
        }
        stage('test') {
            steps {
                echo "test success"
            }
        }
                stage('depoly') {
            steps {
                sh 'deploy success'
            }
        }
    }
}



---

pipeline {
    agent any
    stages {
        stage('git_checkout') {
            steps {
               git branch: 'develop', url: 'https://github.com/cloud-blitz/angular-java.git'
            }
        }
        stage('build') {
            steps {
                 echo "build success"
            }
        }
        stage('test') {
            steps {
                echo "test success"
            }
        }
                stage('depoly') {
            steps {
               echo "deploy success"
            }
        }
    }
}