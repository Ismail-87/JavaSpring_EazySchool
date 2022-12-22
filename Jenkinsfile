pipeline { 
    agent any 
   
    stages {
        stage('Build') { 
            steps { 
                sh 'mvn clean compile package'
            }
        }
        stage('Test'){
            steps {
				sh 'mvn test'               
            }
        }
        
    }
}