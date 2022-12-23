pipeline { 
    agent any 
    
   tools {
   		maven '3.8.6'
   		jdk 'JAVA_HOME'
  	 }
  	 
  	
    stages {
        
              
        stage('Build'){
            steps {
				sh 'mvn clean compile package'            
            }
        }
        
    }
}