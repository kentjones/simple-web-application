pipeline {
  agent any
//   parameters{
//       string(name: 'tomcat_dev', defaultValue: 'laptop:8010', description: 'Tomcat development server')
//       string(name: 'tomcat_prod', defaultValue: 'laptop:9010', description: 'Tomcat production server')
//   }
  triggers{
      pollSCM('* * * * *')
  }
  tools {
    gradle 'Gradle-5.4'
  }

  stages {
    stage('Build') {
      steps {
        bat 'gradle :clean :assemble'
      }
      post {
        success {
          echo 'now archiving....'
          archiveArtifacts artifacts: '**/libs/*.war'
        }
      }
    }
    stage('Test') {
        steps {
            bat 'gradle :test'
        }
        post {
            success {
                echo 'test passed'
            }
            failure {
                echo 'test failed'
            }
        }
    }
    stage('Deploy to Dev'){
      when {
        expression {
            currentBuild.result == null || currentBuild.result == 'SUCCESS'
        }
      }
      steps {
        build job: 'deploy-to-dev'
      }
       post {
         success {
           echo 'Deployed war application to tomcat'
         }
         failure {
             echo 'Deploy to Dev failed. Nothing deployed'
         }
       }
    }
  }
}