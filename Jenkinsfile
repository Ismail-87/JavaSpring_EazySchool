pipeline { 
    agent any 
	tools {
        maven 'MAVEN_HOME'
          }
	triggers {
        cron('H/2 * * * *')
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
                junit 'target/surefire_reports/*.xml' 
            }
        }
        
    }
}