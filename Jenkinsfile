pipeline { 
    agent any 
    
    environment {
    JAVA_HOME ='C:\Program Files\Java\jdk-11.0.16'
    MAVEN_HOME = 'C:\Program Files\maven'
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