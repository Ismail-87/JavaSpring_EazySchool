pipeline { 
    agent any 
    
   tools {
   		M3
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