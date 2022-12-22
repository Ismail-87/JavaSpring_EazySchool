pipeline { 
    agent any 
    
   tools {
   		maven '3.8.1'
  	 }
   
    stages {
        stage('Maven') { 
            steps { 
                sh 'mvn --version'
            }
        }
        stage('Build'){
            steps {
				sh 'mvn clean compile'               
            }
        }
        
    }
}