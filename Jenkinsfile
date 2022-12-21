pipeline { 
    agent any 
	tools {
        maven 'MAVEN_HOME'
          }
	
    stages {
        stage('Build') { 
            steps { 
                sh 'maven clean compile' 
            }
        }
        stage('Test'){
            steps {
                sh 'maven test'
                
            }
        }
        
    }
}