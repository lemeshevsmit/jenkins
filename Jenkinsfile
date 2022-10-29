pipeline {
agent any
  stages {
    stage('Build') {
      steps {
          git 'https://github.com/lemeshevsmit/jenkins.git'
          sh 'mvn clean package'
        }
      }
    }
  }
}