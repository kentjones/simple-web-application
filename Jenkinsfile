pipeline {

  agent any

  parameters{
      string(name: 'tomcat_dev', defaultValue: "laptop:8010", description: 'Tomcat development server')
      string(name: 'tomcat_dev_credentials', defaultValue: "affeff9e-8903-42b1-93e2-8425515f7e07", description: 'Tomcat development credentials')
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

        deploy adapters: [tomcat9(credentialsId: "affeff9e-8903-42b1-93e2-8425515f7e07", path: '', url: 'http://laptop:8010/')], contextPath: 'hello', war: '**/libs/*.war'
      }
//       steps {
//         bat "winscp -i /home/jenkins/tomcat-demo.pem **/target/*.war tomcat@{params.tomcat_dev}:/var/lib/tomcat7/webapps"
//       }
       post {
         success {
           echo 'Deployed war application to tomcat'
         }
         failure {
             echo 'Deploy to Dev failed. Nothing deployed'
             emailext body: 'The simple build application failed', recipientProviders: [buildUser()], subject: 'Simple Web Application build failed', to: 'kent.developer@use.startmail.com'
         }
       }
    }
  }
}