pipeline {
agent any
  stages {
    stage('1-Build') {
      steps {
        echo 'Start build zip archive'
        sh 'mvn clean package -P=zip'
        echo 'Zip created correct'
        }
      }
    stage('2-Test') {
      steps {
        echo 'Start testing project'
        sh 'mvn package'
        echo 'Testing end'
        }
      }
    stage('3-Run') {
     steps {
       echo 'Start run project'
       sh 'unzip -o target/jenkins-1.0-SNAPSHOT.zip'
       sh 'java -jar -Dtype=short jenkins-1.0-SNAPSHOT.jar -print'
       echo 'Build successful'
       }
      }
    }
}