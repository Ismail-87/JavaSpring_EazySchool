pipeline { 
    agent any 
    
   tools {
   		maven 'MAVEN_HOME'
   		jdk 'JAVA_HOME'
  	 }
  	 
  	
  stages {  
              
        stage('clean') {
            steps {
		 echo "clean process started"   
		 sh 'mvn clean'
		 echo "clean process end"
                 }
       	 }
        
        stage('Compile') {
            steps {
		 echo "compile process started"
		sh 'mvn compile' 
		 echo "compile process end"
	    	}
   	 }
       stage('package'){
      	   steps {
		 echo "package process started"
       	       sh 'mvn package'

		   echo "package completed"

		}
	 }
  }
}
