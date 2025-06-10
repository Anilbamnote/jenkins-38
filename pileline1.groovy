// pipeline {
//     agent any
//     stages {
//         stage('git_pull') {
//             steps {
//                 echo "pull success"
//             }
//         }
//         stage('build') {
//             steps {
//                  echo "build success"
//             }
//         }
//         stage('test') {
//             steps {
//                 echo "test success"
//             }
//         }
//                 stage('depoly') {
//             steps {
//                 sh 'deploy success'
//             }
//         }
//     }
// }





pipeline {
    agent {label 'node'}
    stages {
        stage('git_checkout') {
            steps {
               git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'
            }
        }
        stage('build') {
            steps {
                sh '/opt/maven/bin/mvn clean package'
            }
        }
        stage('test') {
            steps {
               withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-cread') {
               sh '/opt/maven/bin/mvn sonar:sonar'
           }
                // sh '/opt/maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://43.201.115.47:9000 -Dsonar.login=1c03dfa37d9ca3815be1d37b1681fcd05b2d6d7b'
            }
        }
        stage('Quality_Gate') {
            steps {
               timeout(10) {
              
            }
               waitForQualityGate true
            }
        }
        stage('artifact-upload') {
            steps {
               sh 'aws s3 cp target/studentapp-2.2-SNAPSHOT.war  s3://my-terra-bucket009991'
           }
        }
        //  stage('depoly') {
        //     steps {
        //        deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'deploy-id', path: '', url: 'http://13.125.59.132:8080/')], contextPath: '/', war: '**/*.war'
        //     }
        // }
    }
}