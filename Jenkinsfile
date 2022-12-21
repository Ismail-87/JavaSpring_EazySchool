pipeline { 
    agent any 
    tools {
    maven 'maven-3.9.1.2184'
    }

    stages {
        stage('Build') { 
            steps { 
                sh 'mvn clean package'
            }
        }
        stage('Test'){
            steps {
				sh 'mvn test'               
            }
        }
        
    }
}