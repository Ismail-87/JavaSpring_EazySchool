pipeline { 
    agent any 
    tools {
    "MAVEN_HOME"
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