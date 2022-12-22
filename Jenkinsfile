pipeline { 
    agent any 
    tools {
      maven ${MAVEN_HOME}
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