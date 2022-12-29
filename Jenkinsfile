pipeline { 
    agent any 
    
   tools {
   		maven 'MAVEN_HOME'
   		jdk 'JAVA_HOME'
  	 }
  	 
  	
  stages {  
              
        stage('clean') {
            steps {
		 sh 'mvn clean'            
                 }
       	 }
        
        stage('Compile') {
            steps {
		sh 'mvn compile'  
	    	}
   	 }
       stage('package'){
      	   steps {
       	       sh 'mvn package'
		}
	 }
	 stage('docker image'){
      	   steps {
       	       sh 'docker image build -t Ismail/javaApp_v1 ./'
		}
	 }
  }
}
