pipeline { 
    agent any 
    tools {
      maven '3.8.1'
    }

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