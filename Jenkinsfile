pipeline {
  agent any
  parameters{
      string(name: 'tomcat_dev', defaultValue: 'laptop:8010', description: 'Tomcat development server')
      string(name: 'tomcat_dev_credentials', defaultValue: 'affeff9e-8903-42b1-93e2-8425515f7e07', description: 'Tomcat development credentials')
      string(name: 'tomcat_prod', defaultValue: 'laptop:9010', description: 'Tomcat production server')
  }
  triggers{
      pollSCM('* * * * *')
  }
  tools {
    gradle 'Gradle-Current'
  }

  stages {
    stage('Build') {
      steps {
        bat './gradlew :clean :assemble'
      }
      post {
        success {
          echo 'now archiving....'
          archiveArtifacts artifacts: '**/libs/*.war', followSymlinks: false
        }
      }
    }
    stage('Test') {
        steps {
            bat './gradlew :test'
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

        copyArtifacts filter: '**/libs/*war', fingerprintArtifacts: true, projectName: 'simple-web-application', selector: lastWithArtifacts()

        deploy adapters: [tomcat9(credentialsId: "${tomcat_dev_credentials}", path: '', url: "http://${tomcat_dev}/")], contextPath: 'hello', war: '**/libs/*.war'
      }
//       steps {
//         build job: 'deploy-to-dev'
//       }
       post {
         success {
           echo 'Deployed war application to tomcat'
         }
         failure {
             echo 'Deploy to Dev failed. Nothing deployed'
             emailext body: 'The simple build application failed', recipientProviders: [buildUser()], subject: 'Simple Web Application build failed', to: 'kent.accounts@use.startmail.com'
         }
       }
    }
  }
}