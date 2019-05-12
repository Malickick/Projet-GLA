pipeline{
agent any
stages{
    stage('Build'){
      step{
        sh '/home/aly/Téléchargements/maven-3.6.1 clean install'
        }
    }
    stage('Test'){
      step{
        sh '/home/aly/Téléchargements/maven-3.6.1 test'
      }
    }
   }
 } 
