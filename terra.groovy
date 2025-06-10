pipeline {
    agent {label 'node'}
    stages {
        stage('Pull') {
            steps {
               git branch: 'main', url: 'https://github.com/Anilbamnote/cdec38.git'
            }
        }
        stage('Test') {
            steps {
                sh ''' 
                    cd terraform/eks
                    terraform init
                    terraform plan
                    
                    '''
            }
        }
        stage('Deploy') {
            steps {
                sh ''' cd terraform/eks
                    terraform init
                    terraform apply --auto-approve'''
            }
        }
    }
}